<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="css_links" value="${['assets/css/profile-form.css']}" />

<%@include file="/WEB-INF/comps/dashboard/header.jsp" %>

<div class="app-form-cnt container h-100 d-flex flex-column justify-content-center">

    <div class="text-center mb-3">
        <h1 class="h3">Update Profile</h1>
        <c:if test="${not empty form_error}">
            <p class="text-sm text-danger">${form_error}</p>
        </c:if>
    </div>

	<form class="d-flex flex-column align-items-center" action="" method="POST" enctype="multipart/form-data">
       
		<div class="mb-3">
			  <input value="${userBean.getName()}" type="text" class="form-control form-control-lg inp " placeholder="Type the Username.." x-model="fileName" name="name"  id="name" placeholder="Username">
		</div>
		<div class="mb-3">
			<input value="${userBean.getBirthDay()}" name="birth-day" type="date" class="form-control form-control-lg" id="birth-day" placeholder="Password">
		</div>
		<div class="mb-3">
            <label class="custum-file-upload" for="avatar">
                <div class="icon" id="imagePreview">
                       <img style="object-fit: cover;" src="${userBean.getAvatar()}" class="UpdateImage" alt="">
                </div>
                <div class="text">
                    <span>Upload Profile Image</span>
                </div>
                <input class="avatar" type="file" name="avatar" id="avatar" placeholder="Avatar" accept="image/*" onchange="previewImage(this)">
            </label>
		</div>
		<div class="d-flex justify-content-center"> <!-- Center the button -->
            <button class="btn-box theme-btn" data-bs-toggle="modal" data-bs-target="#partInfoModal1" type="submit">
               Update Profile
            </button>
        </div>
		
	</form>
	
</div>

<script>
  function previewImage(input) {
      var preview = document.getElementById('imagePreview');
      var file = input.files[0];
      var reader = new FileReader();
  
      reader.onloadend = function () {
          // Create an image element
          var img = document.createElement('img');
          img.src = reader.result;
          img.alt = 'Image Preview';
          img.style.maxWidth = '100%';
          img.style.height = '100px';
  
          // Clear previous content
          preview.innerHTML = '';
  
          // Append the new image to the preview
          preview.appendChild(img);
      };
  
      if (file) {
          reader.readAsDataURL(file);
      } else {
          // If no file is selected, clear the preview
          preview.innerHTML = '';
      }
  }
</script>

<%@include file="/WEB-INF/comps/dashboard/footer.jsp" %>