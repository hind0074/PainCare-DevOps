document.addEventListener('DOMContentLoaded', function () {
    // Add custom font whitelist
   
    var quill = new Quill('#description', {
        modules: {
            toolbar: [
                 [{ header: [1, 2, 3, 4, 5, false] }],
                 ['bold', 'italic', 'underline', 'strike'], // Add underline and strike-through
                 ['link', 'blockquote', 'code-block'],
                 [{ list: 'ordered' }, { list: 'bullet' }],
                 [{ align: [] }], // Add text alignment
                     // Add video embed button
                 ['clean']
            ]
        },
        placeholder: 'Type your content',
        theme: 'snow'
    });
    var toolbar = document.querySelector('.ql-toolbar');
    if (toolbar) {
        toolbar.style.width = '1000px'; // Set the desired width in pixels or any other CSS unit
    }
    quill.on('text-change', function () {
        document.getElementById('descriptioninput').value = quill.root.innerHTML;
    });
});


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

        // Clear previous content and append the new image
        preview.innerHTML = '';
        preview.appendChild(img);
    };

    if (file) {
        reader.readAsDataURL(file);
    } else {
        // If no file is selected, clear the preview
     
    }
}