<?php 
 ob_start();
      session_start(); 
	  require_once('twitteroauth.php'); 
	  require_once('config.php'); 
	  
	   $connection = new TwitterOAuth(CONSUMER_KEY, CONSUMER_SECRET);
 
/* Get temporary credentials. */
$request_token = $connection->getRequestToken(OAUTH_CALLBACK);
		
		   $_SESSION['oauth_token']=$token=$request_token['oauth_token']; 
		   $_SESSION['oauth_token_secret']=$request_token['oauth_token_secret']; 
		   
		   
		  switch ($connection->http_code) {
  case 200:
    /* Build authorize URL and redirect user to Twitter. */
    $url = $connection->getAuthorizeURL($token);
    header('Location: ' . $url); 
    break;
  default:
    /* Show notification if something went wrong. */
    echo 'Could not connect to Twitter. Refresh the page or try again later.';
}
ob_end_flush();
			  
			  ?>
			  