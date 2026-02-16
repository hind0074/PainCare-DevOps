        <!--Scroll to top-->
        <button class="scroll-top scroll-to-target" data-target="html">
            <span class="fas fa-arrow-up"></span>
        </button>


        <!-- jequery plugins -->
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/owl.js"></script>
        <script src="assets/js/wow.js"></script>
        <script src="assets/js/appear.js"></script>
        <script src="assets/js/scrollbar.js"></script>
        <script src="assets/js/pagenav.js"></script>

        <!-- main-js -->
        <script src="assets/js/script.js"></script>

        <script>
            $(window).on('load', function() {
                $('#handle-preloader').fadeOut();
                $('.preloader').delay(350).fadeOut('slow');
                $('body').delay(350).css({'overflow':'visible'});
            });
        </script>

    </body>
</html>
