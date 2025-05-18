document.addEventListener("DOMContentLoaded", () => {
  const backgroundTransition = document.querySelector(".background-transition")
  const howItWorksSection = document.querySelector(".how-it-works-section")

  const getTransitionStartPosition = () => {
    return window.innerHeight * 0.5 
  }

  const updateTransition = () => {
    const scrollPosition = window.scrollY
    const startPosition = getTransitionStartPosition()
    const transitionDistance = window.innerHeight * 0.5 

    if (scrollPosition > startPosition) {
      
      const opacity = Math.min(1, (scrollPosition - startPosition) / transitionDistance)
      backgroundTransition.style.opacity = opacity.toString()
    } else {
      backgroundTransition.style.opacity = "0"
    }
  }

  window.addEventListener("scroll", updateTransition)

  updateTransition()
})
