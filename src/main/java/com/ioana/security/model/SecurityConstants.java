package com.ioana.security.model;

public class SecurityConstants {

    private SecurityConstants() {}

    public static final String SECRET_KEY = "zofgzobyzeEKKMtyyGlXxW4wMpzWPKIfg2Fm7BZjH4oXk0vNW7p9fFgdIKOHi52XRwRx69+WwGl8DxyaLbzC3tqa4sq7540SWQXodpisr4QeDZEB6W1fbiPkILF2mYjkSm6F/mLO5WblMlJtBUp9HlMlGdGXz9ClIaspPJqTEjyMTMJqsLE5uckNCsec23wIYHd3t/Zct9eYu/QDefn6W6qMmMGnuf1cVNZvfRXFIOVKHamYsYx8hdC1bZ+qrVweRgXSDKTiAC5mTpERHg7uC0ISwvXtbVFwUoAvBCT+wnJldizF3bv/m+oM4N/pQrFqZ6fLXWcXhkGSQl9Tn3F67AwhZtMLAnrKhkgqplCI1AbzpGa4POoeODqXhiGY6AVo6fAjaZXmqwMevxPYU3iaIAk0SDErS/NXs5TvJQDhkAJAv2Pf4qCBVM5sJEGh3lPA1rBnW/hcTtZjj8yUc+d9lFQFLXvpcGI4E86B6dR0XVSBsW6R8LTT2lQEw7kDp6lzQCoeSvP4sWwDg/Igjn9svIdm/AfoXuCy4fwE8oQNJjM6OlmtCyhRY/PJU+isZQQI2aOlNrWWTGFKAk+yt29qgWValwocjOaQVnJtd/faA5k6zZ7Vu6AXhxthah7cmbnclsa/eoQHN4jap43jE8FprgpzpI8CYL1EYiWz4rw06ZaoJSudOEo+PaIqh+G0u3Ki";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final long EXPIRATION_TIME = 60; // 60 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users/register";
    public static final String LOGIN_URL = "/api/users/login";
}
