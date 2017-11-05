// The root URL for the RESTful services
var rootURL = "http://localhost:8080/Rest-hw12/api";


$('#btnshowevent').click(function() {

	showevent()
});
$('#btnupdateuser').click(function() {

	editUser()
});
$('#btndeleteuser').click(function() {

	deleteUser()
});
$('#btnrole').click(function() {

	changerole()
});

$('#btnSearch').click(function() {
	//search($('#searchKey').val());
	search()
});

$('#btnshowall').click(function() {

	findAll()
});

$('#btnlogin').click(function() {
	login()
});

$('#btnregister').click(function() {

	insertuser()
});
$('#btnregisterr').click(function() {

	window.open("http://localhost:8080/Rest-hw12/Register.html");
});

$("#btnSave").click(function() {

	addContact()

});
$("#btnUpdate").click(function() {

	editContact()

});

$("#btnDelete").click(function() {

	DeleteContact()

});

//Add a new contact
function addContact() {
	console.log('addContact');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/addcontact',
		data : formToJsonContact(),
		success : function(responseText) {
			alert(responseText);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status + " " + jqXHR.responseText);
		}
	});
}

function showevent() {
	console.log('showevent');
	$.ajax({
		type : 'GET',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/showevent',
		dataType : "json", // data type of response
		success : renderListevent
	});
}
function findAll() {
	console.log('findAll');
	$.ajax({
		type : 'GET',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/showcontact',
		dataType : "json", // data type of response
		success : renderList
	});
}
function search() {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type : 'GET',
		url : rootURL + '/FinalProject/search/' + $('#searchKey').val(),
		dataType : "json",
		success : renderList
	});
}

function insertuser() {
	console.log('insertuser');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/user',
		data : formToJSON(),
	}).done(function(responseText) {

		window.open("http://localhost:8080/Rest-hw12/login.html");

	}).fail(function(jqXHR, responseText, error) {
		// Triggered if response status code is NOT 200 (OK)
		alert(jqXHR.status);
	})
}

function login() {
	console.log('login');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/login',
		data : formToJSON(),
	}).done(function(responseText) {
		if (responseText == "no welcome") {
			
			alert("username or password not valid!");
			
		} else if (responseText == "welcome") {
			console.log(' entering a Member');

			//  window.location.href=("http://localhost:8080/Rest-hw12/HomePage.html");		
			window.open("http://localhost:8080/Rest-hw12/HomePage.html");
		}
	}).fail(function(jqXHR, responseText, error) {
		// Triggered if response status code is NOT 200 (OK)
		alert(jqXHR.status);
	})
}

function editContact() {
	console.log('editContact');
	$.ajax({
		type : 'PUT',
		contentType : 'application/json',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/updatecontact/' + $('#contactID').val(),
		data : formToJsonContact(),
		success : function(responseText) {

			alert(responseText);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
		}
	});
}

function DeleteContact() {
	console.log('DeleteContact');
	$.ajax({
		type : 'DELETE',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/deletecontact/' + $('#contactID').val(),
		success : function(responseText) {

			alert(responseText);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
		}
	});
}

function deleteUser() {
	console.log('DeleteUser');
	$.ajax({
		type : 'DELETE',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/deleteuser/' + $('#username').val(),
		success : function(responseText) {

			alert(responseText);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
		}
	});
}

function changerole() {
	console.log('changerole');
	$.ajax({
		type : 'PUT',
		contentType : 'application/json',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/changerole/' + $('#username').val(),
		data : formToJsonuser(),
		success : function(responseText) {

			alert(responseText);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
		}
	});
}

function editUser() {
	console.log('edituser');
	$.ajax({
		type : 'PUT',
		contentType : 'application/json',
		async : false,
		cache : false,
		url : rootURL + '/FinalProject/updateuser/' + $('#username').val(),
		data : formToJsonuserinfo(),
		success : function(responseText) {

			alert(responseText);

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
		}
	});
}


function renderListevent(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#wineList2 li').remove();
	$.each(list, function(index, wine) {
		$('#wineList2').append(
				'<li><a href="#" data-identity="' + wine.id + '">' + wine.id
						+ "&nbsp;&nbsp;&nbsp;&nbsp" + wine.message +  '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + wine.date + "  " + '</a></li>');
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#wineList li').remove();
	$.each(list, function(index, wine) {
		$('#wineList').append(
				'<li><a href="#" data-identity="' + wine.id + '">' + wine.id
						+ " &nbsp;" + wine.name + "&nbsp;&nbsp;  " + wine.family + " &nbsp; "
						+ wine.cellphone + " &nbsp; " + wine.homephone + " &nbsp; "
						+ wine.email + '</a></li>');
	});
}
//For adding and editing contact
function formToJsonContact() {
	return JSON.stringify({
		"name" : $('#name').val(),
		"family" : $('#family').val(),
		"homephone" : $('#homephone').val(),
		"cellphone" : $('#cellphone').val(),
		"email" : $('#email').val()
	});
}

//Helper function to serialize all the form fields into a JSON string
function formToJSON() {

	return JSON.stringify({
		"username" : $('#name').val(),
		"password" : $('#pass').val()

	});
}
//For change role users
function formToJsonuser() {

	return JSON.stringify({
		"username" : $('#name').val(),
		//"password" : $('#pass').val()
		"role" :  $('#role').val()

	});
}

//For change info users
function formToJsonuserinfo() {

	return JSON.stringify({
		"username" : $('#username').val(),
		"password" : $('#pass').val(),
		"role" :  $('#role').val()

	});
}
