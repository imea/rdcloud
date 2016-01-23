// Fonction de désactivation de l'affichage des "tooltips"
//
//var patt_ = new RegExp("\\W","g");
//var patt_tel = new RegExp("\\d{3}-\\d{3}-\\d{4}");
//var patt_date = new RegExp("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$");
//

var patt_ = /\\W","g"/;
var patt_tel = /\\d{3}-\\d{3}-\\d{4}"/;
var patt_date = /^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$/;

	function deactivateTooltips() {
	
		var tooltips = document.querySelectorAll('.tooltip');
		var tooltipsLength = tooltips.length;
		
		for (var i = 0 ; i < tooltipsLength ; i++) {
			tooltips[i].style.display = 'none';
		}
	}

	// La fonction ci-dessous permet de récupérer la "tooltip" qui correspond à notre input
	
	function getTooltip(elements) {
	
		while (elements = elements.nextSibling) {
			if (elements.className === 'tooltip') {
			return elements;
			}
		}	
             return false;
	}
	
	
	// Fonctions de vérification du formulaire, elles renvoient "true" si tout est ok
	
	var check = {}; // On met toutes nos fonctions dans un objet littéral

//	check['id'] = function() {
//		
//		var id = document.getElementById('id_up');
//		var tooltipStyle = getTooltip(id).style;
//
//		if (!id.value.match(patt_) && id.value.length > 6  && id.value.length < 11) {
//			id.className = 'correct';
//			tooltipStyle.display = 'none';
//			return true;
//		} else {
//			id.className = 'incorrect';
//			tooltipStyle.display = 'inline-block';
//			return false;
//		}
////	};

	check['password_up'] = function(pwd) {

		var _pwd  = document.getElementById(pwd);

		var passw = document.getElementById('password_up');
		var tooltipStyle1 = getTooltip(passw).style;

		var passwConf = document.getElementById('password_up_conf');
		var tooltipStyle2 = getTooltip(passwConf).style;

		 if(_pwd === passw){

		 	if (!passw.value.match(patt_) && passw.value.length > 6) {
				passw.className = 'correct';
				tooltipStyle1.display = 'none';
				res = true;
			} else {
				passw.className = 'incorrect';
				tooltipStyle1.display = 'inline-block';
				res = false;
			}
		}
			
		if (passw.value == passwConf.value){
				passwConf.className = 'correct';
				tooltipStyle2.display = 'none';
				res = true;
		}	
		else {
			passwConf.className = 'incorrect';
			tooltipStyle2.display = 'inline-block';
			res = false;
		}
			return res;
	};
	
	check['password_up_confirm'] = check['password_up'];
	

	check['user_last_name_up'] = function(id) {
	
		var nom = document.getElementById(id);
		var tooltipStyle = getTooltip(name).style;
		
		if (!nom.value.match(patt_)) {
			nom.className = 'correct';
			tooltipStyle.display = 'none';
			return true;
		} else {
			nom.className = 'incorrect';
			tooltipStyle.display = 'inline-block';
			return false;
		}
	};
	
	check['user_first_name_up'] = check['user_last_name_up']; // La fonction pour le prénom est la même que celle du nom
	
	check['user_bday_up'] = function() {

		var bday = document.getElementById('user_bday_up');
		var tooltipStyle = getTooltip(bday).style;

		if (bday.value.match(patt_date)) {
			bday.className = 'correct';
			tooltipStyle.display = 'none';
			return true;
		} else {
			bday.className = 'incorrect';
			tooltipStyle.display = 'inline-block';
			return false;
		}
	};

	check['user_phone_up'] = function() {
	
		var phone = document.getElementById('user_phone_up');
		var tooltipStyle = getTooltip(name).style;
		  var pv = phone.value;
                  
		if (pv.match(patt_tel)) {
                  
			phone.className = 'correct';
			tooltipStyle.display = 'none';
			return true;
		} else {
                    alert(pv);
			phone.className = 'incorrect';
			tooltipStyle.display = 'inline-block';
			return false;
		}
	};

	// Mise en place des événements
	
	(function() { // Utilisation d'une IIFE pour éviter les variables globales.
	
		var signUPform = document.getElementById('signUPform');
		var inputs = document.querySelectorAll('input[type=text], input[type=password], input[type=date]');
		var inputsLength = inputs.length;
		
		for (var i = 0 ; i < inputsLength ; i++) {
                   
			inputs[i].addEventListener('keyup', function(e){
                               
				check[e.target.id](e.target.id); // "e.target" représente l'input actuellement modifié
			}, false);
		}
		
		signUPform.addEventListener('submit', function(e) {
			var result = false;
                      
//			for (var i in check) {
//				result = check[i](i) && result;        
//			}
		alert(result);
			if (result) {
				alert('Le formulaire est bien rempli.');
			}
			else{
				alert('Il faut remplir le formulaire.');
				e.preventDefault();
			}	

		}, false);
		
		
	})();

	// Maintenant que tout est initialisé, on peut désactiver les "tooltips"
	
deactivateTooltips();

