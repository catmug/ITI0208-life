context('Actions', () => {
    beforeEach(() => {
        cy.visit('http://localhost:9000/#/expense')
    })

    it('add values', () => {
        // https://on.cypress.io/submit
        cy.get('#login')
            .find('[type="text"]').type('test');
        cy.get('#login').find('[type="password"]').type('test');
        cy.get('#login').submit();
        cy.get('.button').click()
        cy.get('.button').click()
        cy.get('.button').click()


        cy.get('[data-cy="expense-amount"]').focus().type('12555');
        cy.get('[data-cy="expense-comment"]').type('comment');
        cy.get('[data-cy="expense-category"]').select('Meelelahutus')
        cy.get('[data-cy=submit]').click()

        cy.contains('New Expense has been added')
    })

})
