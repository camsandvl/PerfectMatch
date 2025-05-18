document.addEventListener("DOMContentLoaded", () => {
  
  const firstSection = document.querySelector(".first-section")
  const howItWorksSection = document.querySelector(".how-it-works-section")

  
  function adjustSections() {
    
    const firstSectionHeight = firstSection.offsetHeight


    howItWorksSection.style.marginTop = "0"
  }

  adjustSections()
  window.addEventListener("resize", adjustSections)

  const firstButton = document.querySelector(".first-section .title-btn")
  if (firstButton) {
    firstButton.addEventListener("click", (e) => {
      e.preventDefault()
      howItWorksSection.scrollIntoView({ behavior: "smooth" })
    })
  }
})
