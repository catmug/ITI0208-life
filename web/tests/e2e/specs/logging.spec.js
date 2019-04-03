/// <reference types="Cypress" />

context('Logging', () => {
  beforeEach(() => {
    cy.visit('http://localhost:9000')
  })

  it('log in', () => {
    // https://on.cypress.io/submit
    cy.get('#login')
      .find('[type="text"]').type('test');
    cy.get('#login').find('[type="password"]').type('test');
      cy.get('#login').submit();
      cy.get('.button').click()
      cy.get('.button').click()
      cy.get('.button').click()
  })

})
