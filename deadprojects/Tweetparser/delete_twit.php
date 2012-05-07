<?php 
 ob_start();
session_start(); 
	 require_once('twitteroauth.php'); 
	 require_once('config.php'); 
	    
		   if(!$_GET['id'] || $_GET['id']=='')
		   {
		     header('Location:'.$_SERVER['HTTP_REFERER']);
			 }
			 else
			 {
			  $access_token = $_SESSION['access_token'];
			   $connection = new TwitterOAuth(CONSUMER_KEY, CONSUMER_SECRET, $access_token['oauth_token'], $access_token['oauth_token_secret']);
			   
			      $id=intval($_GET['id']);
			   if($connection->post('statuses/destroy',array('id'=>$id)))
			   {
			       ?><script type="text/javascript">alert('Secmis oldugunuz tweet silindi! Yönlendiriliyorsunuz');
				   </script> 
				   <?php 
				    header('Location:index.php');
				   } 
				   else
				   {
				       echo "Hata olustu kodlara göz at!"; 
					   }
					   }
ob_end_flush();
					   ?>
					   
					   
					   