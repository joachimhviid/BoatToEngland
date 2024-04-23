Public class Collision {
    void naiveCollision() {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                if (entities.get(i).isCollidingWith(entities.get(j))) {
                    hits(entities.get(i), entities.get(j));
                }
            }
        }
    }
    void sortedCollision() {
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                if (entities.get(i).getBoundingBox().getMaxX() < entities.get(j).getBoundingBox().getMinX()) {
                    break;
                }
                if (entities.get(i).isCollidingWith(entities.get(j))) {
                    hits(entities.get(i), entities.get(j));
                }
            }
        }
    }
}