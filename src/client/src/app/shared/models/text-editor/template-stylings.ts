export const threeImageTemplateStyling = `
  .post-main-image-container {
    max-height: 45rem;
    overflow: hidden;
  }

  .post-tag {
    padding: 0.50em 0.75em;
    border-radius: 1em;
    font-size: 0.75rem;
    font-weight: bold;
    text-decoration: none;
  }
  
  .post-tag-blue {
    background: #56ccf2;
    background: linear-gradient(to bottom, #2f80ed, #56ccf2);
    color: #fafafa;
  }
  
  .post-main-image {
    width: 100%;
    height: 100%;
    background-size: cover;
    object-fit: cover;
  }
  
  .post-secondary-image {
    width: 30%;
  }
  
  .post-image-left {
    float: left;
    padding-right: 1rem;
    padding-bottom: 0.75rem;
  }
  
  .post-image-right {
    float: right;
    padding-left: 1rem;
    padding-bottom: 0.75rem;
  }
  
  .post-text {
    font-size: 1rem;
    padding-bottom: 0.75rem;
    margin-bottom: 1rem;
    font-weight: 400;
    line-height: 1.5;
  }

  .px-1 {
    padding: 0, 0.75rem
  }
  
  .post-text,
  .post-intro {
    padding: 1.5rem;
  }
  
  h2,
  h3,
  h4 {
    color: #343a40;
  }
  
  h3, h4{
      font-weight: 500;
  }
  
  h2 {
    font-size: 2rem;
  }
  
  h3 {
    font-size: 1.75rem;
  }
  
  h4 {
    font-size: 1.5rem;
  }
  
  .second-post-half {
      padding-top: 3rem;
  }
  
  .first-post-half {
      margin-bottom: 2rem;
  }`;

export const exerciseTemplateStyling = `
.exercise-container {
  padding-top: 5rem;
  padding-bottom: 5rem;
  padding-left: 7rem;
  padding-right: 3rem;
  display: flex;
  flex-direction: row;
  gap: 2rem;
}

.exercise-heading-container,
.exercise-how-to,
.exercise-tips,
.exercise-benefits {
  padding-bottom: 1rem;
}

.exercise-heading {
  font-size: 24px;
}

.exercise-heading,
.exercise-subheading {
  color: #343a40;
  text-transform: uppercase;
  font-weight: 700;
  margin-bottom: 0.5rem;
}

.exercise-muscle-group {
  display: flex;

  div {
    flex-basis: 50%;
  }
}

.exercise-main-muscle-groups, .exercise-synergist-muscle-groups {
  
  p {
    padding-top: 0.5re,;
    padding-bottom: 0.5rem;
    padding-left: 0.5rem;
    margin: 0.5rem;
    color: #fff;
    font-weight: 500;
    border-radius: 4px;
  }
}

.exercise-main-muscle-groups p {
  background-color: red;
}

.exercise-synergist-muscle-groups p {
  background-color: #00bcd4;
  max-width: 85%;
}

gains-muscle-group {
  width: 100%;
}

.exercise-sidebar {
  flex-basis: 30%;
}

.exercise-section {
  padding-right: 4rem;
  padding-left: 4rem;
  width: 100%;
}

.exercise-ordered-list {
  counter-reset: item;

  li {
    counter-increment: item;
    padding: 0.5rem;
  }

  li:before {
    content: counter(item) ". ";
  }
}

.exercise-tips-list {
  li:before {
    content: "•";
    color: #343a40;
    padding: 0.5rem;
  }
}

p,
li {
  font-size: 16px;
  font-weight: 400;
}

strong {
  font-weight: 600;
  color: #343a40;
}

.exercise-gif-container {
  width: 50%;
  margin: auto;
  padding: 1rem;
}

.similar-exercises {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: center;
  align-items: center;
}

.similar-exercise {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 0.5rem;
  padding: 1rem;
  border: 1px solid #343a40;
  border-radius: 1rem;
  box-shadow: 10px 10px 3px #00bcd4;

  a {
    max-width: 200px;
    padding: 1rem;
    text-decoration: none;
    color: #343a40;
    text-transform: uppercase;

    img {
      width: 100%;
      padding: 1rem;
    }
  }

  div {
    p {
      padding-left: 1rem;
    }
  }
}

p {
  margin-bottom: 1rem;
}

`;