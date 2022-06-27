package common

class Uri {
    private static HashMap<String, HashMap<String, String>> uris

    static String getUri(String name){
        return uris[name][Environment.getMode()]
    }

    static void setUris(HashMap<String, HashMap<String, String>> newUris) {
        uris = newUris
    }

}
