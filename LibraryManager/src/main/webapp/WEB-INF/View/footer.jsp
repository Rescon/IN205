<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer class="page-footer">
  <div class="footer-copyright">
    <div class="container">
      Design © 2017 Farooq Designs. All rights reserved. (Modify © 2021 Yu WANG.)
    </div>
  </div>
</footer>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
// Hide sideNav
document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('.button-collapse');
  var options = {
    menuWidth: 300, // Default is 300
    edge: 'left', // Choose the horizontal origin
    closeOnClick: false, // Closes side-nav on <a> clicks, useful for Angular/Meteor
    draggable: true // Choose whether you can drag to open on touch screens
  };
  var instances = M.Sidenav.init(elems, options);

  var elems = document.querySelectorAll('select');
  var instances = M.FormSelect.init(elems, {});
});
</script>
<script src="https://unpkg.com/ionicons@4.4.4/dist/ionicons.js"></script>