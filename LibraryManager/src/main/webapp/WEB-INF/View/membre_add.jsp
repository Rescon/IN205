<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Fiche membre</h1>
      </div>
      <div class="row">
        <div class="container">
          <h5>Création d'un nouveau membre</h5>
          <div class="row">
	        <form action="/LibraryManager/membre_add" method="post" class="col s12">
	          <div class="row">
	            <div class="input-field col s6">
	              <input id="nom" type="text" name="nom">
	              <label for="nom">Nom</label>
	            </div>
	            <div class="input-field col s6">
	              <input id="prenom" type="text" name="prenom">
	              <label for="prenom">Prénom</label>
	            </div>
	          </div>
	          <div class="row">
	            <div class="input-field col s12">
	              <input id="adresse" type="text" name="adresse">
	              <label for="adresse">Adresse</label>
	            </div>
	          </div>
	          <div class="row">
	            <div class="input-field col s6">
	              <input id="email" type="email" name="email">
	              <label for="email">E-mail</label>
	            </div>
	            <div class="input-field col s6">
	              <input id="telephone" type="tel" name="telephone">
	              <label for="telephone">Téléphone</label>
	            </div>
	          </div>
	          <div class="row center">
	            <button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
	            <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	          </div>
	        </form>
	      </div>
        </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
