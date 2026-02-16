<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="css_links" value="${['assets/css/blogs.css']}" />

<style>
    .sidebar-page-container .sidebar .sidebar-post .post-inner .post .post-thumb img {
        width: 100%;
        border-radius: 10px;
        transition: all 500ms ease;
        height: 81px;
    }
    svg.bi.flex-shrink-0.me-2 {
    color: red;
}
</style>

<%@include file="/WEB-INF/comps/header.jsp" %>

<c:set var="activePage" value="x" />

<%@include file="/WEB-INF/comps/navbar.jsp" %>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
          <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
              <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
          </symbol>
          <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
             <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
          </symbol>
          <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
              <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
          </symbol>
        </svg>
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         Are you sure you want to delete this comment?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-danger" id="confirmDeleteButton" data-blog-id="">Yes</button>
        
      </div>
    </div>
  </div>
</div>

<section class="sidebar-page-container blog-details">
    <div class="auto-container">
        <div class="row clearfix">
            <div class="col-lg-8 col-md-12 col-sm-12 content-side">
                <div class="blog-details-content">
                    <div class="news-block-one">
                        <div class="inner-box">
                            <figure class="image-box"><img src="${blog.getImage()}" /></figure>
                            <div class="lower-content">

                                <h2>${blog.getTitle()}</h2>
                                <ul class="post-info clearfix">

                                    <li><img src="${blog.getUserImage()}" />${blog.getUserName()} </li>
                                    <li>${blog.getDate().toString()}</li>

                                </ul>
                                <div class="text">
                                    <p>${blog.getDescription()}</p>



                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="comments-area">
                        <div class="group-title">
                            <h3>Comments</h3>
                        </div>
                        <div class="comment-box">
                            <c:forEach var="comment" items="${comments}">
                                <div class="comment">
                                    <figure class="thumb-box">
                                        <img style="object-fit: cover;" src="${comment.getAvatar()}" width="50" height="50" />
                                    </figure>
                                    <div class="comment-inner">
                                        <div class="info">
                                       <c:choose>
                                         <c:when test="${userBean.getID() eq comment.getUserID()}">
                                               <div class="deleteUser d-flex justify-content-between align-items-center">
                                                  <h4>${comment.getUserName()}</h4>
                                                  <a href="#" class="action-icon theme-btn-icon mr-3" data-toggle="modal" data-target="#exampleModal" onclick="openModalWithData(${comment.getID()},${blog.getID()})">
                                                       <i class="fas fa-trash"></i>
                                                  </a>
                                               </div>
                                         </c:when>
                                         <c:otherwise>
                                              <div class="deleteUser d-flex justify-content-between align-items-center">
                                                  <h4>${comment.getUserName()}</h4>
                                              </div>
                                          </c:otherwise>
                                       </c:choose>
                                       

                                            <span
                                                class="comment-time">${comment.getDate().toString()}</span>
                                        </div>
                                        <p>${comment.getContent()}</p>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="comments-form-area">
                        <div class="group-title">
                            <h3>Leave a Comment</h3>
                        </div>


                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <form method="post" action="comment" id="contact-form" class="default-form">
                                    <div class="row clearfix">

                                        <div class="col-lg-12 col-md-12 col-sm-12 form-group">
                                            <input name="blog_id" type="hidden" value="${blog.getID()}" />
                                            <textarea name="content"
                                                placeholder="Leave A Comment"></textarea>
                                        </div>

                                        <div class="col-lg-12 col-md-12 col-sm-12 form-group message-btn">
                                            <button class="theme-btn" type="submit"
                                                name="submit-form">Submit Now</button>
                                        </div>
                                    </div>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="/PainCare/Login" method="get" id="contact-form"
                                    class="default-form">
                                    <div class="row clearfix">

                                        <div class="col-lg-12 col-md-12 col-sm-12 form-group">
                                            <textarea placeholder="Leave A Comment"></textarea>
                                        </div>

                                        <div class="col-lg-12 col-md-12 col-sm-12 form-group message-btn">
                                            <button class="theme-btn" type="submit">Submit Now</button>
                                        </div>
                                    </div>
                                </form>

                            </c:otherwise>
                        </c:choose>





                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-12 col-sm-12 sidebar-side">
                <div class="sidebar">

                    <div class="sidebar-widget sidebar-post">
                        <div class="widget-title">
                            <h4>Recent Posts</h4>
                        </div>
                        <div class="post-inner">
                            <c:forEach var="blog" items="${blogs}">
                                <div class="post">
                                    <figure class="post-thumb">
                                        <a href="blog?id=${blog.getID()}">
                                            <img style="object-fit: cover;" src="${blog.getImage()}" alt="">
                                        </a>
                                    </figure>
                                    <h5><a href="blog?id=${blog.getID()}">${blog.getTitle()}</a></h5>
                                    <span class="post-date">${blog.getDate().toString()}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<footer class="main-footer">
	<div class="shape-layer"
		style="background-image: url(assets/images/shape/shape-3.png);"></div>
	<div class="anim-icon">
		<div class="icon icon-1 rotate-me"
			style="background-image: url(assets/images/);"></div>
		<div class="icon icon-2 rotate-me"
			style="background-image: url(assets/images/);"></div>
		<div class="icon icon-3 rotate-me"
			style="background-image: url(assets/images/);"></div>
	</div>
	<div class="footer-top">
		<div class="auto-container">
			<div class="widget-section">
				<div class="row clearfix">
					<div class="col-lg-3 col-md-6 col-sm-12 footer-column">
						<div class="logo-widget footer-widget">
							<figure class="footer-logo">
								<a href="index.html"><img style="height: 60px"
									src="assets/images/logo-full.png" alt=""></a>
							</figure>
							<div class="text">
								<p>The best way to prevent and slow down transmission is be
									well informed about the Endometriosis, the disease it causes
									and how it spreads.</p>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-12 footer-column">
						<div class="links-widget service footer-widget">
							<div class="widget-title">
								<h3>Services</h3>
							</div>
							<div class="widget-content">
								<ul class="list clearfix">
									<li><a href="index.html">About Us</a></li>
									<li><a href="index.html">Symptoms</a></li>
									<li><a href="index.html">Prevention</a></li>
									<li><a href="index.html">Our Doctors</a></li>
									<li><a href="index.html">Our Blog</a></li>
									<li><a href="index.html">Contact Us</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-12 footer-column">
						<div class="links-widget region footer-widget">
							<div class="widget-title">
								<h3>Regions</h3>
							</div>
							<div class="widget-content">
								<ul class="list clearfix">
									<li><a href="index.html">America</a></li>
									<li><a href="index.html">Africa</a></li>
									<li><a href="index.html">South East Asia</a></li>
									<li><a href="index.html">Europe</a></li>
									<li><a href="index.html">Australia</a></li>
									<li><a href="index.html">Westenr Pacific</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-12 footer-column">
						<div class="contact-widget footer-widget">
							<div class="widget-title">
								<h3>Contacts</h3>
							</div>
							<ul class="info-list clearfix">
								<li><i class="fas fa-map-marker-alt"></i>Avenue Mohamed Ben
									Abdellah, Avenue Regragui, 10112 Rabat,Ensias</li>
								<li><i class="fas fa-microphone"></i><a
									href="tel:+212 619-214446">+212 619-214446</a></li>
								<li><i class="fas fa-envelope"></i><a
									href="mailto:info@example.com">info@example.com</a></li>
							</ul>
							<ul class="social-links clearfix">
								<li><a href="index.html"><i class="fab fa-facebook-f"></i></a></li>
								<li><a href="index.html"><i class="fab fa-twitter"></i></a></li>
								<li><a href="index.html"><i class="fab fa-vimeo-v"></i></a></li>
								<li><a href="index.html"><i class="fab fa-linkedin-in"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-bottom">
		<div class="auto-container">
			<div class="bottom-inner clearfix">
				<div class="copyright pull-left">
					<p>
						<a href="index.html">PainCare</a> &copy; Project done by a groupe
						of ENSIAS students IDSIT second year
					</p>
				</div>
				<ul class="footer-nav pull-right">
					<li><a href="index.html">Terms of Service</a></li>
					<li><a href="index.html">Privacy Policy</a></li>
				</ul>
			</div>
		</div>
	</div>
</footer>

<script>
function openModalWithData(data1,data2) {
    // Set the data-blog-id attribute
    document.getElementById('confirmDeleteButton').setAttribute('data-comment-id', data1);
    document.getElementById('confirmDeleteButton').setAttribute('data-blog-id', data2);


    // Open the modal
    $('#exampleModal').modal('show');
}

// Add an event listener to the confirmation button
document.getElementById('confirmDeleteButton').addEventListener('click', function() {
    // Retrieve the data-blog-id attribute value
    var commentId = this.getAttribute('data-comment-id');
    var blogId = this.getAttribute('data-blog-id');


    // Perform your action here, for example, redirecting to remove_blog
    window.location.href = 'remove_comment?comment_id=' + commentId;
});
</script>

<%@include file="/WEB-INF/comps/footer.jsp" %>