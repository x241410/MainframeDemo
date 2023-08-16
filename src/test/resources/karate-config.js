function() {    
    
    karate.log('Env Params :', karate.env);
     
     var envName = karate.env;
  
  // var appName = karate.properties['telus.appName'];
     // karate.log('telus.appName system property was:', appName);
  
  var config = karate.read('classpath:config/AppConfig.json');  
  config.myEndPoints = karate.read('classpath:config/EndPoints.json');
  
   if (envName == 'PT140') {
	    config.ENDPOINT_PT140= config.myEndPoints.PT140.ENDPOINT_PT140
  	    
  	} else if (envName == 'NON-PROD') {
	    config.ENDPOINT_NON_PROD= config.myEndPoints.NON_PROD.ENDPOINT_NON_PROD
	   
    } else if (envName == 'PROD') {
    	config.ENDPOINT_PROD= config.myEndPoints.PROD.ENDPOINT_PROD
     

    }
   

  karate.configure('connectTimeout', 116000);
  karate.configure('readTimeout', 116000);
  return config;
}