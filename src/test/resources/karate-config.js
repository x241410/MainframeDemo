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
	    config.ENDPOINT_CMS=config.myEndPoints.NON_PROD.ENDPOINT_CMS_NON_PROD
	    config.ENDPOINT_CLECOSS=config.myEndPoints.NON_PROD.ENDPOINT_CLECOSS_NON_PROD
		config.ENDPOINT_TOLLFREEORDERSTATUS=config.myEndPoints.NON_PROD.ENDPOINT_TOLLFREEORDERSTATUS_NON_PROD
		config.ENDPOINT_CSBA=config.myEndPoints.NON_PROD.ENDPOINT_CSBA_NON_PROD
		config.ENDPOINT_CAMToken=config.myEndPoints.NON_PROD.ENDPOINT_CAMToken_NON_PROD
		config.ENDPOINT_CAM=config.myEndPoints.NON_PROD.ENDPOINT_CAM_NON_PROD
		config.ENDPOINT_CSOMToken=config.myEndPoints.NON_PROD.ENDPOINT_CSOMToken_NON_PROD
        config.ENDPOINT_CSOM=config.myEndPoints.NON_PROD.ENDPOINT_CSOM_NON_PROD

    } else if (envName == 'PROD') {
    	config.ENDPOINT_NON_PROD= config.myEndPoints.PROD.ENDPOINT_PROD
        config.ENDPOINT_CSBA=config.myEndPoints.PROD.ENDPOINT_CSBA_PROD
		config.ENDPOINT_CAMToken=config.myEndPoints.PROD.ENDPOINT_CAMToken_PROD
		config.ENDPOINT_CAM=config.myEndPoints.PROD.ENDPOINT_CAM_PROD
		config.ENDPOINT_CSOMToken=config.myEndPoints.PROD.ENDPOINT_CSOMToken_PROD
        config.ENDPOINT_CSOM=config.myEndPoints.PROD.ENDPOINT_CSOM_PROD

    }
   

  karate.configure('connectTimeout', 116000);
  karate.configure('readTimeout', 116000);
  return config;
}