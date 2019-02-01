package shopping.util;

public enum SwaggerStatus {
    AUTO_LAUNCH_SWAGGER("Auto Launch Mode", true),
    QUIET_MODE("Quiet Mode", false);

    private String name;
    private boolean autoLaunch;

    SwaggerStatus(String name, boolean autoLaunch) {
        this.name = name;
        this.autoLaunch = autoLaunch;
    }

    public boolean autoLaunchSwagger() {
        return this.autoLaunch;
    }
}
