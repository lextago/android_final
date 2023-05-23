// use webview to create web area

WebView myWebView = (WebView) findViewById(R.id.webview);
myWebView.loadUrl("http://www.example.com");


// add textview, textfield and button
// button leads to next page
// textfield data is saved into variable, sent into firebase
// create user class

// Create an object of Firebase Database Reference

DatabaseReference reference ;
reference = FirebaseDatabase.getInstance().getReference();  

// Insert the user-defined object to the database 

reference.child("").setValue(user1); 


