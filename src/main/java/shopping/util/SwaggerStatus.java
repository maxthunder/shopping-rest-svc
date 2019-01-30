package shopping.util;

public enum SwaggerStatus {
    AUTO_LAUNCH_SWAGGER("Auto Launch Swagger", true),
    QUIET_MODE("Quiet Mode", false);

    private String name;
    private boolean autoLaunchSwagger;

    SwaggerStatus(String name, boolean autoLaunchSwagger) {
        this.name = name;
        this.autoLaunchSwagger = autoLaunchSwagger;
    }

    public boolean autoLaunchSwagger() {
        return this.autoLaunchSwagger;
    }
}
