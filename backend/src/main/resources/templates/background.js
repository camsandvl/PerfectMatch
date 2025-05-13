document.addEventListener("mousemove", (e) => {
  const interactive = document.querySelector(".interactive");
  if (interactive) {
    const { clientX: x, clientY: y } = e;
    interactive.style.transform = `translate(${x}px, ${y}px)`;
  }
});
