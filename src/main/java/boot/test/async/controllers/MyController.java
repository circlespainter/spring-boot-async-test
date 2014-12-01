package boot.test.async.controllers;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.SuspendableRunnable;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author circlespainter
 */
@RestController
public class MyController /* implements ErrorController */ {
    private final static String ERROR_PATH = "/error";
    
/*    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    } */

/*    @RequestMapping(ERROR_PATH)
    public String error() {
        return "Error!";
    } */

/*    @ExceptionHandler
    @ResponseBody
    public String handleException(RuntimeException ex) {
        return "Handled exception: " + ex.getMessage();
    } */
    
    @RequestMapping("/myHello")
    @Suspendable
    public String myHello() throws SuspendExecution, InterruptedException {
        Strand.sleep(1000);
        return "Greetings from Spring Boot! Is fiber? " + Strand.currentStrand().isFiber();
    }

    @RequestMapping("/myError")
    public void myError() throws SuspendExecution, InterruptedException {
        Strand.sleep(1000);
        throw new RuntimeException("Test exception; is fiber? " + Strand.currentStrand().isFiber());
    }

    @RequestMapping("/myHelloDeferredThread")
    public DeferredResult<String> myHelloDeferredThread() {
        final DeferredResult<String> ret = new DeferredResult<>(3600000);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
              ret.setResult("Deferred thread greetings from Spring Boot!");
            }
        }).start();
        
        return ret;
    }

    @RequestMapping("/myErrorDeferredThread")
    public DeferredResult<String> myErrorDeferredThread() {
        final DeferredResult<String> ret = new DeferredResult<>(3600000);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new RuntimeException("Test thread deferred exception");
                } catch (Throwable t) {
                    ret.setErrorResult(t);
                }
            }
        }).start();
        
        return ret;
    }

    @RequestMapping("/myHelloDeferredFiber")
    public DeferredResult<String> myHelloDeferredFiber() {
        final DeferredResult<String> ret = new DeferredResult<>(3600000);
        
        new Fiber(new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution, InterruptedException {
                ret.setResult("Deferred fiber greetings from Spring Boot!");
            }
        }).start();
        
        return ret;
    }

    @RequestMapping("/myErrorDeferredFiber")
    public DeferredResult<String> myErrorDeferredFiber() {
        final DeferredResult<String> ret = new DeferredResult<>(3600000);
        
        new Fiber(new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution, InterruptedException {
                try {
                    throw new RuntimeException("Test deferred fiber exception");
                } catch (Throwable t) {
                    ret.setErrorResult(t);
                }
            }
        }).start();
        
        return ret;
    }
}
