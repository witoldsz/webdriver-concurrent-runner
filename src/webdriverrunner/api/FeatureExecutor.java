package webdriverrunner.api;

/**
 *
 * @author Witold Szczerba
 */
public interface FeatureExecutor {
    
    void initialize(int threadPoolSize);
    
    void execute(Class<? extends Feature> featureType);
    
    void awaitTermination() throws Exception;
}
