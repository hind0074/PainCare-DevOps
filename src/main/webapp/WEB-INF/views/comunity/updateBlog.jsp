<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="activePage" value="blogs" />
<c:set var="css_links" value="${['assets/css/quill.snow.css', 'assets/css/blog-form.css']}" />

<%@include file="/WEB-INF/comps/dashboard/header.jsp" %>

<div class="app-form-cnt container h-100 d-flex flex-column justify-content-center">

    <div class="text-center mb-3">
        <h1 class="h3">
            <c:if test="${empty blog}"> Publish Blog </c:if>
            <c:if test="${not empty blog}"> Update Blog </c:if>
        </h1>
        <c:if test="${not empty form_error}">
            <div class="alert alert-danger" role="alert">
                <p class="text-sm text-danger">${form_error}</p>
            </div>
        </c:if>
    </div>


    <form class="d-flex flex-column align-items-center" action="" method="POST" enctype="multipart/form-data">

        <div class="mb-3">

            <input name="title" type="text" class="form-control control-lg" id="title" placeholder="Title"
            <c:if test="${not empty blog}">value="${blog.getTitle()}"</c:if>>
        </div>
        <div class="mb-3">
            <input name="description" id="descriptioninput" type="hidden" <c:if test="${not empty blog}">value="${blog.getTitle()}"</c:if>>

            <div name="description" class="form-control" id="description" placeholder="Description">
                <c:if test="${not empty blog}">${blog.getDescription()}</c:if>
            </div>


        </div>

        <div class="mb-3">
            <div class="container">
                <label class="custum-file-upload" for="image">
                    <div class="icon" id="imagePreview">
                         <img style="object-fit: cover;" src="${blog.getImage()}" class="UpdateImage" alt="">
                    </div>
                    <div class="text">
                        <span>Upload Blog Image</span>
                    </div>
                    <input name="image" type="file" class="form-control" id="image" placeholder="Image" accept="image/*" onchange="previewImage(this)">
                    
                </label>




            </div>

        </div>

        <div class="d-flex justify-content-center"> <!-- Center the button -->
            <button class="btn-box theme-btn" data-bs-toggle="modal" data-bs-target="#partInfoModal1"
                type="submit">
                Update Blog
            </button>
        </div>
    </form>

</div>

<script src="assets/js/quill.js"></script>

<script src="assets/js/blogs-form.js"></script>

<%@include file="/WEB-INF/comps/dashboard/footer.jsp" %>