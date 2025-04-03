package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import main.najah.code.RecipeBook;
import main.najah.code.Recipe;

@DisplayName("RecipeBook functionality tests")
class RecipeBookTest {

    RecipeBook recipeBook;

    @BeforeAll
    static void initAll() {
        System.out.println("=== Starting RecipeBook Tests ===");
    }

    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();
        System.out.println("[Setup] Initialized new RecipeBook");
    }

    @Test
    @DisplayName("should correctly add unique recipes")
    void testAddValidRecipe() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Pizza");
        recipeBook.addRecipe(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setName("Burger");
        recipeBook.addRecipe(recipe2);

        assertEquals("Pizza", recipeBook.getRecipes()[0].getName());
        assertEquals("Burger", recipeBook.getRecipes()[1].getName());
    }

    @Test
    @DisplayName("should not allow adding same recipe twice")
    void testAddDuplicateRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Pizza");
        recipeBook.addRecipe(recipe);

        assertFalse(recipeBook.addRecipe(recipe), "duplicate recipe should not be accepted");
    }

    @Test
    @DisplayName("should remove recipe and return its name")
    void testDeleteExistingRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Pizza");
        recipeBook.addRecipe(recipe);

        String deletedName = recipeBook.deleteRecipe(0);
        assertEquals("Pizza", deletedName, "deleted recipe name should match original");
        assertNull(recipeBook.getRecipes()[0], "recipe should be null after deletion");
    }

    @Test
    @DisplayName("should return null when deleting from empty list")
    void testDeleteNonExistingRecipe() {
        assertNull(recipeBook.deleteRecipe(0), "no recipe to delete should return null");
    }

    @Test
    @DisplayName("should update recipe correctly at valid index")
    void testEditExistingRecipe() {
        Recipe originalRecipe = new Recipe();
        originalRecipe.setName("Pizza");
        recipeBook.addRecipe(originalRecipe);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Pasta");
        String editedName = recipeBook.editRecipe(0, newRecipe);

        assertEquals("Pizza", editedName, "should return original name before update");
        assertEquals("Pasta", recipeBook.getRecipes()[0].getName(), "recipe should reflect new name");
    }

    @Test
    @DisplayName("should return null when trying to edit invalid index")
    void testEditNonExistingRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Pasta");
        assertNull(recipeBook.editRecipe(0, newRecipe), "editing non-existing recipe should yield null");
    }

    @AfterEach
    void tearDown() {
        System.out.println("[Teardown] Test completed");
    }

    @AfterAll
    static void finishAll() {
        System.out.println("=== RecipeBook Tests Finished ===");
    }
}
