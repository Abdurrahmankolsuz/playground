<?php 
 
     session_start(); 
	 require_once('twitteroauth.php'); 
	 require_once('config.php'); 
	 

if (empty($_SESSION['access_token']) || empty($_SESSION['access_token']['oauth_token']) || empty($_SESSION['access_token']['oauth_token_secret'])) {


	       ?> <a href='redirect.php'>Sign in with Twitter</a>
		   <?php 
		   }
		   else
		   
		   {
          ?> <a href='logout.php'>Logout</a><br /> <?php
		         $access_token = $_SESSION['access_token'];
		        $connection = new TwitterOAuth(CONSUMER_KEY, CONSUMER_SECRET, $access_token['oauth_token'], $access_token['oauth_token_secret']);
	$content = $connection->get('account/verify_credentials');
 echo 'Username:'.'   '.$content->screen_name."<br />"; 
			  			  echo 'Followers count:'.'  '.$content->followers_count."<br />"; 
                           echo ' Your twitter created at:'.date("d/m/Y",strtotime($content->created_at)).'<br />';
			
		   echo "<br />"."Retweeted by You"."<br />";
               $retweets=$connection->get('statuses/retweeted_by_me');
foreach($retweets as $mesaj)
{
  $id=$mesaj->id;
     echo $mesaj->id.'    '.$mesaj->text.'   '.date("d/m/y",strtotime($mesaj->created_at)).'<a href="delete_twit.php?id='.$id.'">DELETE</a>'.'<br />';
} "<br />"; 
}
echo "<br />";
		   

		echo 'Your 10 tweets with id: <br />'; 
        		 
			  $owntweets=$connection->get('statuses/user_timeline',array('count'=>10)); 
     
          foreach ($owntweets as $display){
  $id=$display->id;
             echo $display->id.'    '.$display->text.'    '.date("d/m/y",strtotime($display->created_at)).'   '.'<a href="delete_twit.php?id='.$id.'">DELETE</a>'.'<br />';
} 
			 
  

echo ' Your mentions:<br />';
  $mentions=$connection->get('statuses/mentions'); 
		  foreach($mentions as $mention)
{
     echo $mention->id.'   '.$mention->text.'<br />';
}

  echo '<br /><br />';

	  ?>
			  
			  