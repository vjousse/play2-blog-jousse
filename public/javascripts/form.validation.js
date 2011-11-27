$(function() {  
  $('.error').hide();  
  $("#contact-submit").click(function() {  
    // validate and process form here  
  
    $('.error').hide();  
      var name = $("input#contact-name").val();  
        if (name == "") {  
      $("label#contact-name-error").show();  
      $("input#contact-name").focus();  
      return false;  
    } 	
	
	$('.error').hide();  
      var email = $("input#contact-email").val();  
        if (email == "") {  
      $("label#contact-email-error").show();  
      $("input#contact-email").focus();  
      return false;  
    } 	
	
	$('.error').hide();  
      var message = $("textarea#contact-message").val();  
        if (message == "") {  
      $("label#contact-message-error").show();  
      $("textarea#contact-message").focus();  
      return false;  
    } 	
  	
	var dataString = 'name='+ name + '&email=' + email + '&message=' + message;
	  
//alert (dataString);return false;  
$.ajax({  
  type: "POST",  
  url: "PHP/process.php",  
  data: dataString,  
  success: function() {  
    $('#contact-form').html("<fieldset><div id='message'></div></fieldset>");  
    $('#message').html("<h5>Contact Form Submitted!</h5>")  
    .append("<p>We will be in touch soon.</p>")  
    .hide()  
    .fadeIn(1500, function() {  
      $('#message').append("");  
    });  
  }  
});  
return false; 

  });  
}); 