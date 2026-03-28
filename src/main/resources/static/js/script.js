document.addEventListener('DOMContentLoaded', () => {
    // Current year for footer
    const yearEl = document.getElementById('year');
    if (yearEl) {
        yearEl.textContent = new Date().getFullYear();
    }

    // Intersection Observer for scroll animations (fade-in)
    const fadeElements = document.querySelectorAll('.project-card, .section-heading, .glass-panel');
    
    // initially add fade-in class
    fadeElements.forEach(el => el.classList.add('fade-in'));

    const observerOptions = {
        threshold: 0.15,
        rootMargin: "0px 0px -50px 0px"
    };

    const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('visible');
                observer.unobserve(entry.target); // Stop observing once it's visible
            }
        });
    }, observerOptions);

    fadeElements.forEach(el => observer.observe(el));

    // Contact Form AJAX Submit
    const contactForm = document.getElementById('contactForm');
    if (contactForm) {
        contactForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const btn = contactForm.querySelector('button[type="submit"]');
            btn.textContent = 'Gönderiliyor...';
            btn.disabled = true;

            fetch(contactForm.action, {
                method: 'POST',
                body: new FormData(contactForm),
                headers: {
                    'Accept': 'application/json'
                }
            })
            .then(response => response.json())
            .then(data => {
                if(data.success || data.ok || (data.status === 200)) {
                    contactForm.innerHTML = '<div class="alert-success">Harika! Mesajınız başarıyla e-postama iletildi. En kısa sürede dönüş yapacağım.</div>';
                } else {
                    btn.textContent = 'Gönderilemedi!';
                }
            })
            .catch(error => {
                btn.textContent = 'Hata! Lütfen tekrar deneyin';
                btn.disabled = false;
            });
        });
    }
});
