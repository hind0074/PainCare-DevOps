<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="css_links" value="${['assets/css/form-page.css']}" />

<%@include file="/WEB-INF/comps/header.jsp" %>

<div class="app-form-cnt container">
	<div class="text-center mb-3">
        <img width="100" height="100" src="assets/images/logo.png" class="logo" alt="Logo">
        <h1 class="h3">Register</h1>
        <c:if test="${not empty form_error}">
            <p class="text-sm text-danger">${form_error}</p>
        </c:if>
    </div>

	<form action="" method="POST">
		<div class="mb-3">
			<label for="name">Username :</label>
			<input name="name" type="text" class="form-control" id="name" placeholder="Username" />
		</div>
		<div class="mb-3">
			<label for="email">Email :</label>
			<input name="email" type="email" class="form-control" id="email" placeholder="Email">
		</div>
		<div class="mb-3">
			<label for="password">Password :</label>
			<input name="password" type="password" class="form-control" id="password" placeholder="Password">
		</div>
		<div class="mb-3">
			<label for="password2">Confirm Password :</label>
			<input name="password2" type="password" class="form-control" id="password2" placeholder="Password">
		</div>
			<div class="more-btn centred">
				<button class="theme-btn" type="submit">
					Registre
				</button>
			</div>
	</form>
	
</div>

<%@include file="/WEB-INF/comps/footer.jsp" %>