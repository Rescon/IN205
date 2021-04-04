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
        <h5>Détails du membre n°${membre.id}</h5>
          <div class="row">
	        <form action="/LibraryManager/membre_details?id=${membre.id}" method="post" class="col s12">
	          <div class="row">
	            <div class="input-field col s4">
	              <input id="nom" type="text" value="${membre.nom}" name="nom"> <!-- TODO : remplacer nomDuMembre par le nom du membre -->
	              <label for="nom">Nom</label>
	            </div>
	            <div class="input-field col s4">
	              <input id="prenom" type="text" value="${membre.prenom}" name="prenom"> <!-- TODO : remplacer prenomDuMembre par le pr�nom du membre -->
	              <label for="prenom">Prénom</label>
	            </div>
	            <div class="input-field col s4">
	              <select name="abonnement" class="browser-default">
				    <c:choose>
					  <c:when test="${membre.abonnement == 'BASIC'}">
					    <option value="BASIC" selected>Abonnement BASIC</option>
					    <option value="PREMIUM">Abonnement PREMIUM</option>
					    <option value="VIP">Abonnement VIP</option>
					  </c:when>
					  <c:when test="${membre.abonnement == 'PREMIUM'}">
					    <option value="BASIC">Abonnement BASIC</option>
					    <option value="PREMIUM" selected>Abonnement PREMIUM</option>
					    <option value="VIP">Abonnement VIP</option>
					  </c:when>
					  <c:when test="${membre.abonnement == 'VIP'}">
					    <option value="BASIC">Abonnement BASIC</option>
					    <option value="PREMIUM">Abonnement PREMIUM</option>
					    <option value="VIP" selected>Abonnement VIP</option>
					  </c:when>
					  <c:otherwise>
					    <option value="" disabled selected>---</option>
					    <option value="BASIC">Abonnement BASIC</option>
					    <option value="PREMIUM">Abonnement PREMIUM</option>
					    <option value="VIP">Abonnement VIP</option>
					  </c:otherwise>
				    </c:choose>
	              </select>
	            </div>
	          </div>
	          <div class="row">
	            <div class="input-field col s12">
	              <input id="adresse" type="text"  value="${membre.adresse}" name="adresse">
	              <label for="adresse">Adresse</label>
	            </div>
	          </div>
	          <div class="row">
	            <div class="input-field col s6">
	              <input id="email" type="email" value="${membre.email}" name="email">
	              <label for="email">E-mail</label>
	            </div>
	            <div class="input-field col s6">
	              <input id="telephone" type="tel" value="${membre.telephone}" name="telephone">
	              <label for="telephone">Téléphone</label>
	            </div>
	          </div>
	          <div class="row center">
	            <button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
	            <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	          </div>
	        </form>
	        <form action="/LibraryManager/membre_delete" method="get" class="col s12">
	          <input type="hidden" value="${membre.id}" name="id">
	          <div class="row center">
	            <button class="btn waves-effect waves-light red" type="submit">Supprimer le membre
	              <i class="material-icons right">delete</i>
	            </button>
	          </div>
	        </form>
	      </div>
          <div class="row">
            <div class="col s12">
	          <table class="striped">
                <thead>
                  <tr>
                    <th>Livre</th>
                    <th>Date d'emprunt</th>
                    <th>Retour</th>
                  </tr>
                </thead>
                <tbody id="results">
			      <c:if test="${!emprunts.isEmpty()}">
				    <c:forEach items="${emprunts}" var="emprunt">
					  <tr>
						<td>"${emprunt.livre.titre}" de ${emprunt.livre.auteur}</td>
						<td>${emprunt.dateEmprunt}</td>
						<td>
						  <a href="emprunt_return?id=${emprunt.id}"><ion-icon class="table-item" name="log-in"></ion-icon></a>
						</td>
					  </tr>
				    </c:forEach>
			      </c:if>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>