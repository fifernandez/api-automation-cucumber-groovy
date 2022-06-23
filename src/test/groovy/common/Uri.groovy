package common

class Uri {
    private static HashMap<String, HashMap<String, String>> uris = [
            "posts": [
                "private": "null",
                "public": "/posts"
            ],
            "comments": [
                "private": "null",
                "public": "/comments"
            ],
            "albums": [
                "private": "null",
                "public": "/albums"
            ],
            "photos": [
                "private": "null",
                "public": "/photos"
            ],
            "users": [
                "private": "null",
                "public": "/users"
            ],
            "todos": [
                "private": "null",
                "public": "/todos"
            ]
    ]

    static String getUri(String name){
        return uris[name][Environment.getMode()]
    }

}
