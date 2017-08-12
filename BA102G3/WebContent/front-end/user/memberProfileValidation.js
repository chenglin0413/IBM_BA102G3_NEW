$(document).ready(function(){

		$('#registration-form').validate({
	    rules: {
 		 user_account: {
	        minlength: 2,
	        required: true
	      },
		  
 		 user_lastname: {
	        minlength: 1,
	        required: true
	      },

 		 user_firstname: {
	        minlength: 1,
	        required: true
	      },
		  		  
	      user_email: {
	        required: true,
	        email: true
	      },
		  		  
	    },
			highlight: function(element) {
				$(element).closest('.control-group').removeClass('success').addClass('error');
			},
			success: function(element) {
				element
				.text('OK!').addClass('valid')
				.closest('.control-group').removeClass('error').addClass('success');
			}
	  });

}); // end document.ready