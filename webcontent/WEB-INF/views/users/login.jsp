<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="css_links" value="${['assets/css/form-page.css']}" />

<%@include file="/WEB-INF/comps/header.jsp" %>
<body>
<div class="app-form-cnt container">

    <div class="text-center mb-3">
        <img width="100" height="100" src="assets/images/logo.png" class="logo" alt="Logo">
        <h1 class="h3">Login</h1>
        <c:if test="${not empty form_error}">
            <p class="text-sm text-danger">${form_error}</p>
        </c:if>
		<p>Dont have account ?, try to register <a class="text-underline text-primary" href="register">Register</a></p>
    </div>

	<form action="" method="POST">
		<div class="mb-3">
			<label for="email">Email :</label>
			<input name="email" type="email" class="form-control" id="email" placeholder="Email">
		</div>
		<div class="mb-3">
			<label for="password">Password :</label>
			<input name="password" type="password" class="form-control" id="password" placeholder="Password">
		</div>
		<div class="mb-3 d-flex align-items-center">
			<input class="mx-1" name="remember" type="checkbox" id="remember">
			<label for="remember" class="m-0">Remember me</label>
		</div>
		<div class="more-btn centred">
			<button class="theme-btn" type="submit">
				Sign in
			</button>
		</div>

		</form>
	
</div>
</body>
<%@include file="/WEB-INF/comps/footer.jsp" %>