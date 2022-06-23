package common

class BasePath {
    private static HashMap<String, HashMap<String, String>> basePaths = [
            'local': [
                    'private': '',
                    'public' : ''
            ],
            'dev'  : [
                    'private': '',
                    'public' : ''
            ],
            'qa'   : [
                    'private': '',
                    'public' : ''
            ],
            'stage': [
                    'private': '',
                    'public' : ''
            ],
            'prod' : [
                    'private': '',
                    'public' : 'https://jsonplaceholder.typicode.com'
            ]
    ]

    static String getBasePath() {
        return basePaths.get(Environment.getEnvironment()).get(Environment.getMode());
    }

}
