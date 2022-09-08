const paymentStart = () => {
	console. log ( "payment started. . ") ;
	var amount = $("#payment_field") .val () ;
	console.log (amount);
	if (amount == "" || amount == null) {
		alert ("amount is required !!");
		return;
	}


	$.ajax(
		{
			url:'/pay',
			data:JSON.stringify({  amount:  amount, info: 'order_request'}),
			contentType: 'application/json',
			type: 'POST',
			dataType: 'json',
			success:function (a){
				console.log("Sharwari");
				if (a.status == "created") {
					console.log("Sharwari");
					let options={
						key: 'rzp_test_fwH8eL6IVorLNF',
						amount: a.amount,
						Currency: "INR",
						name: " Flight Booking Payment",
						description: "Flight Fare ",
						image:"https://th.bing.com/th/id/OIP.-fmOZ6VFYz5SnqTSZhEv8wHaE8?pid=ImgDet&w=900&h=600&rs=1",
						order_id: a.id,
						handler:function (a) {
						console. log (a.razorpay_payement_id)
						console. log ( a.razorpay_order_id)
						console. log (razorpay_signature)
						console.log ( ' payment successful !!')
						alert("congrates !! Payment successful !!")
						},

						prefill: { name: "", email: "", contact: "" }, 
						notes: { address: "Razorpay Corporate Office"}
						,
						theme: { color: "#3399cc"}

					};

					let rzp = new Razorpay(options) ;
					
					rzp.on ( "payment.failed", function (response) {
					console. log(response.error. code) ;
					console. log (response.error.description)
					console. log (response. error. source) ;
					console. log(response.error. step) ;
					console. log(response.error. reason ) ;
					console. log ( response. error.metadata.order_id);
					console. log ( response.error.metadata.payment_id);
					alert("Oops payment failed !!");
					});

					rzp.open();

				}

			},
			//invoked wh success
			error:function (error) {
				console.log (error+"erorrrr")
				alert("something went wrong !!"+error)
			}
			//invoked when error

		}
	)



};