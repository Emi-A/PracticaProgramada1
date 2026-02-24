function mostrarImagen(input) {
  const img = document.getElementById('blah');
  if (!img) return;

  if (input.files && input.files[0]) {
    const reader = new FileReader();
    reader.onload = e => img.src = e.target.result;
    reader.readAsDataURL(input.files[0]);
  }
}