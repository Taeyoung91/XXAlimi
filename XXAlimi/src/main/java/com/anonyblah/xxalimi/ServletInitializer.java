package com.anonyblah.xxalimi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(XxAlimiApplication.class);
	}

}

/*
 * 
 * <script> window.fbAsyncInit = function() { FB.init({ appId :
 * '477637539100413', xfbml : true, version : 'v2.5' }); };
 * 
 * (function(d, s, id){ var js, fjs = d.getElementsByTagName(s)[0]; if
 * (d.getElementById(id)) {return;} js = d.createElement(s); js.id = id; js.src
 * = "//connect.facebook.net/en_US/sdk.js"; fjs.parentNode.insertBefore(js,
 * fjs); }(document, 'script', 'facebook-jssdk')); </script>
 */