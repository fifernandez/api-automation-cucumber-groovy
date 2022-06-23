package common

class Environment {
    private static String defaultEnv = 'prod'
    private static String selectedEnv = null
    private static String defaultMode = 'public'
    private static String selectedMode = null
    private static ArrayList<String> environments = ['local', 'dev', 'qa', 'stage', 'prod']
    private static ArrayList<String> modes = ['private', 'public']

    static void setEnvs(ArrayList<String> newEnvs) {
        environments = newEnvs
    }

    static void setModes(ArrayList<String> newModes) {
        modes = newModes
    }

    static void setDefaultEnv(String newDefaultEnv) {
        defaultEnv = newDefaultEnv;
    }

    static void setDefaultMode(String newDefaultMode) {
        defaultMode = newDefaultMode;
    }

    static String getEnvironment() {
        if (selectedEnv == null) {
            String consoleEnv = System.getProperty("env")
            if (consoleEnv == null) {
                println "Environment not sent. Going to use default: ${defaultEnv}"
                selectedEnv = defaultEnv
            } else if (!envIsAvailable(consoleEnv)) {
                println "Environment sent in parameter is not available. Sent: ${consoleEnv}"
                println "Going to use default: ${defaultEnv}"
                selectedEnv = defaultEnv
            } else {
                println "Selected env: ${consoleEnv}"
                selectedEnv = consoleEnv
            }
        }
        return selectedEnv
    }

    static String getMode() {
        if (selectedMode == null) {
            String consoleMode = System.getProperty("mode")
            if (consoleMode == null) {
                println "Mode not sent. Going to use default: ${defaultMode}"
                selectedMode = defaultMode
            } else if (!modeIsAvailable(consoleMode)) {
                println "Mode sent in parameter is not available. Sent: ${consoleMode}"
                println "Going to use default: ${defaultMode}"
                selectedMode = defaultMode
            } else {
                println "Selected mode: ${consoleMode}"
                selectedMode = consoleMode
            }
        }
        return selectedMode
    }

    static boolean envIsAvailable(String desireEnv) {
        return environments.contains(desireEnv)
    }

    static boolean modeIsAvailable(String mode) {
        return modes.contains(mode)
    }

    static boolean isPrivateMode() {
        return selectedMode == 'private'
    }

}
