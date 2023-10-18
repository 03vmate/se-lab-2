package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

  @Test
  void fire_Success() {
    // Arrange
    TorpedoStore store = new TorpedoStore(1);

    // Act
    boolean result = store.fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  void fireEmpty() {
    TorpedoStore store = new TorpedoStore(0);
    assert(store.isEmpty());
    try {
      store.fire(1);
      fail("No exception thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("numberOfTorpedos", e.getMessage());
    }
  }

  @Test
  void FireMoreThanAvailable() {
    TorpedoStore store = new TorpedoStore(1);
    assert(!store.isEmpty());
    boolean result = store.fire(1);
    assertEquals(true, result);
    assert(store.isEmpty());
    try {
      store.fire(1);
      fail("No exception thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("numberOfTorpedos", e.getMessage());
    }

  }

  @Test
  void testRemaining() {
    TorpedoStore store = new TorpedoStore(100);
    assertEquals(100, store.getTorpedoCount());
    store.fire(1);
    assertEquals(99, store.getTorpedoCount());
    store.fire(98);
    assertEquals(1, store.getTorpedoCount());
    store.fire(1);
    assertEquals(0, store.getTorpedoCount());
  }

  @Test
  void FireFailChanceTest() {
    TorpedoStore store = new TorpedoStore(100000, 0.5);
    int success = 0;
    for (int i = 0; i < 100000; i++) {
      if (store.fire(1)) {
        success++;
      }
    }
    assertEquals(50000, success, 500);
  }
}