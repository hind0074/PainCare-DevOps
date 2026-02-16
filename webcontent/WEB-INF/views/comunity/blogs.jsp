<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="/WEB-INF/comps/header.jsp" %>

<c:set var="activePage" value="blogs" />

<%@include file="/WEB-INF/comps/navbar.jsp" %>

<section class="page-title centred">
    <div class="auto-container">
        <div class="content-box">
            <h1>Blogs</h1>
            <ul class="bread-crumb clearfix">
                <li><a href="home">Home</a></li>
                <li>Blogs </li>
            </ul>
        </div>
    </div>
</section>

<section class="news-section blog-grid p-0">
    <div class="auto-container">
        <div class="row clearfix">
            <c:forEach var="blog" items="${blogs}">
                <div class="col-lg-4 col-md-6 col-sm-12 news-block  container">
                    <div class="news-block-one wow fadeInUp animated animated" data-wow-delay="00ms"
                        data-wow-duration="1500ms">
                        <div class="inner-box">
                            <figure class="image-box blog-image">
                                <a href="blog?id=${blog.getID()}">
                                    <img src="${blog.getImage()}" />
                                </a>
                            </figure>
                            <div class="lower-content">

                                <h3><a href="blog-details.html">${blog.getTitle()}</a></h3>
                                <ul class="post-info clearfix">
                                    <li><img src="${blog.getUserImage()}" />${blog.getUserName()} </li>
                                    <li>${blog.getDate().toString()}</li>

                                </ul>
                                <p></p>
                                <div class="btn-box"><a href="Blog?id=${blog.getID()}">See Details</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="pagination-wrapper centred">
            <ul class="pagination clearfix">
                <c:forEach begin="1" end="${totalPages}" var="page">
                    <li>
                        <c:if test="${page == currentPage}">
                            <a href="/PainCare/BrowseBlogs?page=${page}" class="active">${page}</a>
                        </c:if>
                        <c:if test="${page != currentPage}">
                            <a href="/PainCare/BrowseBlogs?page=${page}">${page}</a>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
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
								<a href="index.html"><img style="height: 60px" src="assets/images/logo-full.png" alt=""></a>
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
						<a href="index.html">PainCare</a> &copy; Project done by a groupe of ENSIAS students IDSIT second year
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

<%@include file="/WEB-INF/comps/footer.jsp" %>