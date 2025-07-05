package tx.copp.oak.balu.livingroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GPTasciimovement extends JPanel implements KeyListener {
    // Player world position (actual position in the infinite world)
    int playerWorldX = 0;
    int playerWorldY = 0;
    
    // Camera/viewport offset
    int cameraX = 0;
    int cameraY = 0;
    
    // Screen center where player appears
    int screenCenterX = 400;
    int screenCenterY = 300;
    
    // Tree storage - now stores world coordinates
    ArrayList<Tree> trees = new ArrayList<>();
    Random random = new Random();
    
    // Terrain generation
    int tileSize = 50; // Size of each terrain "tile"
    ArrayList<TerrainTile> generatedTiles = new ArrayList<>();
    
    // Tree interaction
    Tree nearbyTree = null;
    boolean showCutPrompt = false;
    boolean eKeyPressed = false; // Track if E key is currently pressed
    
    // Currency system
    int woodCount = 0;
    
    class Tree {
        int worldX, worldY;
        String[] treeArt;
        TreeSize size;
        boolean isFalling = false;
        boolean startedFalling = false; // New flag to prevent multiple cuts
        boolean woodAwarded = false; // Track if wood has been awarded for this tree
        long fallStartTime = 0;
        int fallAnimationDuration = 1000; // 1 second animation
        
        enum TreeSize {
            SMALL, MEDIUM, LARGE, GIANT
        }
        
        Tree(int worldX, int worldY) {
            this.worldX = worldX;
            this.worldY = worldY;
            this.size = generateRandomSize();
            this.treeArt = generateRandomTree();
        }
        
        TreeSize generateRandomSize() {
            Random sizeRandom = new Random();
            int rand = sizeRandom.nextInt(100);
            if (rand < 40) return TreeSize.SMALL;      // 40% chance
            else if (rand < 70) return TreeSize.MEDIUM; // 30% chance
            else if (rand < 90) return TreeSize.LARGE;  // 20% chance
            else return TreeSize.GIANT;                 // 10% chance
        }
        
        String[] generateRandomTree() {
            ArrayList<String> treeParts = new ArrayList<>();
            
            switch (size) {
                case SMALL:
                    // Small trees - single layer
                    String[] smallTops = {"^", " ^ ", "^^^"};
                    treeParts.add(smallTops[random.nextInt(smallTops.length)]);
                    treeParts.add(" | ");
                    break;
                    
                case MEDIUM:
                    // Medium trees - 2 layers
                    String[] mediumTops = {"^^^", "^^^^^", " ^^^ "};
                    treeParts.add(mediumTops[random.nextInt(mediumTops.length)]);
                    treeParts.add("^^^^^");
                    treeParts.add(" || ");
                    break;
                    
                case LARGE:
                    // Large trees - 3-4 layers, much taller
                    treeParts.add("  ^^^  ");
                    treeParts.add(" ^^^^^ ");
                    treeParts.add("^^^^^^^");
                    treeParts.add("^^^^^^^");
                    treeParts.add(" ||| ");
                    break;
                    
                case GIANT:
                    // Giant trees - 5-6 layers, very tall
                    treeParts.add("   ^^^   ");
                    treeParts.add("  ^^^^^  ");
                    treeParts.add(" ^^^^^^^ ");
                    treeParts.add("^^^^^^^^^");
                    treeParts.add("^^^^^^^^^");
                    treeParts.add("^^^^^^^^^");
                    treeParts.add(" |||| ");
                    break;
            }
            
            return treeParts.toArray(new String[0]);
        }
        
        int getWoodReward() {
            switch (size) {
                case SMALL: return random.nextInt(2) + 1;   // 1-2 wood
                case MEDIUM: return random.nextInt(3) + 2;  // 2-4 wood
                case LARGE: return random.nextInt(4) + 4;   // 4-7 wood
                case GIANT: return random.nextInt(6) + 8;   // 8-13 wood
                default: return 2;
            }
        }
        
        Color getTreeColor() {
            switch (size) {
                case SMALL: return new Color(50, 205, 50);    // Light green
                case MEDIUM: return new Color(34, 139, 34);   // Forest green
                case LARGE: return new Color(0, 100, 0);      // Dark green
                case GIANT: return new Color(0, 80, 0);       // Very dark green
                default: return new Color(34, 139, 34);
            }
        }
        
        Color getTrunkColor() {
            switch (size) {
                case SMALL: return new Color(139, 90, 43);    // Light brown
                case MEDIUM: return new Color(101, 67, 33);   // Medium brown
                case LARGE: return new Color(83, 53, 10);     // Dark brown
                case GIANT: return new Color(61, 43, 31);     // Very dark brown
                default: return new Color(101, 67, 33);
            }
        }
        
        int getCollisionWidth() {
            switch (size) {
                case SMALL: return 8;
                case MEDIUM: return 12;
                case LARGE: return 16;
                case GIANT: return 20;
                default: return 12;
            }
        }
        
        int getCollisionHeight() {
            switch (size) {
                case SMALL: return 15;
                case MEDIUM: return 25;
                case LARGE: return 50;  // Increased for taller trees
                case GIANT: return 70;  // Increased for taller trees
                default: return 25;
            }
        }
        
        void startFalling() {
            if (!startedFalling) { // Only start falling once
                isFalling = true;
                startedFalling = true;
                fallStartTime = System.currentTimeMillis();
            }
        }
        
        boolean isFullyFallen() {
            return isFalling && (System.currentTimeMillis() - fallStartTime) >= fallAnimationDuration;
        }
        
        boolean justFinishedFalling() {
            return isFalling && (System.currentTimeMillis() - fallStartTime) >= fallAnimationDuration && !woodAwarded;
        }
        
        float getFallProgress() {
            if (!isFalling) return 0f;
            long elapsed = System.currentTimeMillis() - fallStartTime;
            return Math.min(1f, (float) elapsed / fallAnimationDuration);
        }
        
        // Check if tree is visible on screen
        boolean isVisible(int camX, int camY, int screenWidth, int screenHeight) {
            int screenX = worldX - camX;
            int screenY = worldY - camY;
            // Increased visibility range for tall trees
            return screenX > -50 && screenX < screenWidth + 50 && 
                   screenY > -100 && screenY < screenHeight + 100;
        }
        
        // Get tree height for rendering
        int getTreeHeight() {
            return treeArt.length * 16; // Approximate height based on number of parts
        }
    }
    
    class TerrainTile {
        int tileX, tileY; // Tile coordinates
        char terrainType;
        
        TerrainTile(int tileX, int tileY) {
            this.tileX = tileX;
            this.tileY = tileY;
            // Generate terrain based on position (creates patterns)
            generateTerrain();
        }
        
        void generateTerrain() {
            // Check if this tile should be part of a path
            if (isOnPath()) {
                terrainType = '='; // Path
                return;
            }
            
            // Use tile coordinates to create consistent terrain
            Random tileRandom = new Random((long) tileX * 1000 + tileY);
            float noise = (float) (Math.sin(tileX * 0.1) + Math.cos(tileY * 0.1));
            float noise2 = (float) (Math.sin(tileX * 0.05) * Math.cos(tileY * 0.08));
            
            // Combine multiple noise sources for more realistic terrain
            float combined = noise + noise2 * 0.5f;
            
            if (combined > 0.8) {
                terrainType = '^'; // Mountains
            } else if (combined > 0.3) {
                terrainType = '"'; // Tall grass
            } else if (combined > -0.1) {
                terrainType = '.'; // Short grass
            } else if (combined > -0.5) {
                terrainType = ','; // Sand/dirt
            } else {
                terrainType = '~'; // Water
            }
        }
        
        boolean isOnPath() {
            // Create winding paths - horizontal and vertical main paths
            boolean onHorizontalPath = Math.abs(tileY % 20) <= 1; // Horizontal path every 20 tiles
            boolean onVerticalPath = Math.abs(tileX % 25) <= 1;   // Vertical path every 25 tiles
            
            // Add some diagonal connecting paths
            boolean onDiagonalPath = Math.abs((tileX + tileY) % 30) <= 1 || 
                                   Math.abs((tileX - tileY) % 35) <= 1;
            
            return onHorizontalPath || onVerticalPath || onDiagonalPath;
        }
    }

    public GPTasciimovement() {
        JFrame frame = new JFrame("Pokemon-Style Infinite World");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setVisible(true);
        
        // Generate initial area around player
        generateWorldAroundPlayer();
        
        // Start animation timer for smooth falling animations
        Timer animationTimer = new Timer(16, e -> repaint()); // ~60 FPS
        animationTimer.start();
    }
    
    void generateWorldAroundPlayer() {
        // Generate terrain tiles around current camera position
        int startTileX = (cameraX - 400) / tileSize - 1;
        int endTileX = (cameraX + 1200) / tileSize + 1;
        int startTileY = (cameraY - 300) / tileSize - 1;
        int endTileY = (cameraY + 900) / tileSize + 1;
        
        // Generate terrain tiles
        for (int tx = startTileX; tx <= endTileX; tx++) {
            for (int ty = startTileY; ty <= endTileY; ty++) {
                if (!tileExists(tx, ty)) {
                    generatedTiles.add(new TerrainTile(tx, ty));
                }
            }
        }
        
        // Generate trees for new areas
        generateTreesInArea(startTileX * tileSize, startTileY * tileSize, 
                           (endTileX - startTileX + 1) * tileSize, 
                           (endTileY - startTileY + 1) * tileSize);
        
        // Clean up distant tiles and trees to prevent memory issues
        cleanupDistantObjects();
    }
    
    boolean tileExists(int tileX, int tileY) {
        for (TerrainTile tile : generatedTiles) {
            if (tile.tileX == tileX && tile.tileY == tileY) {
                return true;
            }
        }
        return false;
    }
    
    void generateTreesInArea(int worldX, int worldY, int width, int height) {
        Random areaRandom = new Random((long) worldX + worldY * 10000);
        int numTrees = width * height / 4000; // Reduced tree density for more spacing
        
        for (int i = 0; i < numTrees; i++) {
            int treeWorldX = worldX + areaRandom.nextInt(width);
            int treeWorldY = worldY + areaRandom.nextInt(height);
            
            // Don't place tree too close to origin (spawn point)
            if (Math.abs(treeWorldX) > 60 || Math.abs(treeWorldY) > 60) {
                // Check if location is suitable for tree (not on path or water)
                if (canPlaceTreeAt(treeWorldX, treeWorldY)) {
                    // Check if tree already exists here - increased spacing requirement
                    boolean exists = false;
                    for (Tree tree : trees) {
                        if (Math.abs(tree.worldX - treeWorldX) < 80 && 
                            Math.abs(tree.worldY - treeWorldY) < 80) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        trees.add(new Tree(treeWorldX, treeWorldY));
                    }
                }
            }
        }
    }
    
    boolean canPlaceTreeAt(int worldX, int worldY) {
        // Convert world coordinates to tile coordinates
        int tileX = worldX / tileSize;
        int tileY = worldY / tileSize;
        
        // Create a temporary tile to check terrain type
        TerrainTile tempTile = new TerrainTile(tileX, tileY);
        
        // Don't place trees on paths, water, or mountains
        return tempTile.terrainType != '=' && tempTile.terrainType != '~' && tempTile.terrainType != '^';
    }
    
    // New method to check water collision for player movement
    boolean checkWaterCollision(int newWorldX, int newWorldY) {
        // Player collision box
        int playerSize = 10;
        
        // Check all four corners of the player's collision box
        int[] checkX = {newWorldX - playerSize, newWorldX + playerSize, newWorldX - playerSize, newWorldX + playerSize};
        int[] checkY = {newWorldY - playerSize, newWorldY - playerSize, newWorldY + playerSize, newWorldY + playerSize};
        
        for (int i = 0; i < 4; i++) {
            // Convert world coordinates to tile coordinates
            int tileX = checkX[i] / tileSize;
            int tileY = checkY[i] / tileSize;
            
            // Create a temporary tile to check terrain type
            TerrainTile tempTile = new TerrainTile(tileX, tileY);
            
            // If any corner is on water, block movement
            if (tempTile.terrainType == '~') {
                return true; // Water collision detected
            }
        }
        
        return false; // No water collision
    }
    
    boolean checkTreeCollision(int newWorldX, int newWorldY) {
        // Player collision box (slightly smaller than visual player)
        int playerSize = 10;
        
        for (Tree tree : trees) {
            // Only check collision with standing trees (not falling or fallen ones)
            if (!tree.isFalling) {
                // Use tree size for collision detection
                int treeHitboxWidth = tree.getCollisionWidth();
                int treeHitboxHeight = tree.getCollisionHeight();
                
                // Tree hitbox is centered on the tree position but focused on trunk area
                int treeLeft = tree.worldX - treeHitboxWidth/2;
                int treeRight = tree.worldX + treeHitboxWidth/2;
                int treeTop = tree.worldY - 5; // Slightly above tree base
                int treeBottom = tree.worldY + treeHitboxHeight - 5;
                
                // Check if player would collide with this tree's hitbox
                if (newWorldX + playerSize > treeLeft &&
                    newWorldX - playerSize < treeRight &&
                    newWorldY + playerSize > treeTop &&
                    newWorldY - playerSize < treeBottom) {
                    return true; // Collision detected
                }
            }
        }
        return false; // No collision
    }
    
    void checkNearbyTrees() {
        Tree previousNearbyTree = nearbyTree;
        nearbyTree = null;
        showCutPrompt = false;
        
        // Check if player is near any tree that's not falling
        for (Tree tree : trees) {
            if (!tree.isFalling) {
                double distance = Math.sqrt(Math.pow(playerWorldX - tree.worldX, 2) + 
                                          Math.pow(playerWorldY - tree.worldY, 2));
                
                // If within 50 pixels of a tree, show prompt (increased range)
                if (distance <= 50) {
                    nearbyTree = tree;
                    showCutPrompt = true;
                    break;
                }
            }
        }
        
        // If we moved away from a tree while holding E, reset the flag
        if (previousNearbyTree != nearbyTree) {
            // Player moved to a different tree or no tree, allow cutting again
        }
    }
    
    void cutDownTree() {
        if (nearbyTree != null && !nearbyTree.startedFalling && eKeyPressed) {
            nearbyTree.startFalling();
            // Don't reset nearbyTree immediately - let the animation play
        }
    }
    
    void cleanupDistantObjects() {
        // Remove tiles far from camera
        generatedTiles.removeIf(tile -> 
            Math.abs(tile.tileX * tileSize - cameraX) > 1500 || 
            Math.abs(tile.tileY * tileSize - cameraY) > 1500);
        
        // Remove trees far from camera (but not if they're currently falling)
        trees.removeIf(tree -> 
            !tree.isFalling && (Math.abs(tree.worldX - cameraX) > 1500 || 
            Math.abs(tree.worldY - cameraY) > 1500));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Black background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw terrain tiles
        g.setFont(new Font("Monospaced", Font.BOLD, 12));
        for (TerrainTile tile : generatedTiles) {
            int screenX = tile.tileX * tileSize - cameraX;
            int screenY = tile.tileY * tileSize - cameraY;
            
            if (screenX > -tileSize && screenX < getWidth() + tileSize &&
                screenY > -tileSize && screenY < getHeight() + tileSize) {
                
                // Set realistic colors based on terrain type
                switch (tile.terrainType) {
                    case '~': g.setColor(new Color(64, 164, 223)); break;    // Water - blue
                    case '.': g.setColor(new Color(124, 252, 0)); break;     // Short grass - light green
                    case '"': g.setColor(new Color(34, 139, 34)); break;    // Tall grass - forest green
                    case ',': g.setColor(new Color(210, 180, 140)); break;  // Sand/dirt - tan
                    case '^': g.setColor(new Color(105, 105, 105)); break;  // Mountains - dark gray
                    case '=': g.setColor(new Color(139, 90, 43)); break;    // Path - brown
                }
                
                // Fill terrain tile with solid color first
                g.fillRect(screenX, screenY, tileSize, tileSize);
                
                // Add darker border for definition
                g.setColor(g.getColor().darker());
                g.drawRect(screenX, screenY, tileSize-1, tileSize-1);
                
                // Add texture pattern on top
                g.setColor(g.getColor().brighter().brighter());
                for (int x = 0; x < tileSize; x += 12) {
                    for (int y = 0; y < tileSize; y += 12) {
                        // Only draw some texture dots for variety
                        if ((x + y + tile.tileX + tile.tileY) % 3 == 0) {
                            g.drawString("·", screenX + x, screenY + y + 10);
                        }
                    }
                }
            }
        }

        // Draw trees with better colors and falling animation
        g.setFont(new Font("Monospaced", Font.BOLD, 16));
        FontMetrics fm = g.getFontMetrics();
        
        // Check for newly fallen trees and award wood
        for (Tree tree : trees) {
            if (tree.justFinishedFalling()) {
                // Tree just finished falling, award wood based on tree size
                int woodReward = tree.getWoodReward();
                woodCount += woodReward;
                tree.woodAwarded = true;
                System.out.println("Wood awarded! +" + woodReward + " (" + tree.size + " tree) Total: " + woodCount); // Debug output
            }
        }
        
        // Remove fully fallen trees
        trees.removeIf(Tree::isFullyFallen);
        
        for (Tree tree : trees) {
            if (tree.isVisible(cameraX, cameraY, getWidth(), getHeight())) {
                int screenX = tree.worldX - cameraX;
                int screenY = tree.worldY - cameraY;
                
                if (tree.isFalling) {
                    // Falling animation
                    float progress = tree.getFallProgress();
                    
                    // Calculate rotation angle (0 to 90 degrees)
                    double angle = progress * Math.PI / 2; // 90 degrees in radians
                    
                    // Save current transform
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    // Rotate around the base of the tree
                    g2d.rotate(angle, screenX, screenY + 10);
                    
                    // Tree becomes more transparent as it falls
                    int alpha = (int) (255 * (1 - progress * 0.5)); // Fade to 50% transparency
                    
                    // Draw all parts of the falling tree
                    Color treeColor = tree.getTreeColor();
                    Color trunkColor = tree.getTrunkColor();
                    
                    for (int i = 0; i < tree.treeArt.length; i++) {
                        String part = tree.treeArt[i];
                        int partWidth = fm.stringWidth(part);
                        
                        // Determine if this part is foliage or trunk
                        boolean isTrunk = i == tree.treeArt.length - 1; // Last part is trunk
                        
                        if (isTrunk) {
                            g2d.setColor(new Color(trunkColor.getRed(), trunkColor.getGreen(), trunkColor.getBlue(), alpha));
                        } else {
                            g2d.setColor(new Color(treeColor.getRed(), treeColor.getGreen(), treeColor.getBlue(), alpha));
                        }
                        
                        g2d.drawString(part, screenX - partWidth/2, screenY - (tree.treeArt.length - i - 1) * 16);
                    }
                    
                    // Reset transform
                    g2d.rotate(-angle, screenX, screenY + 10);
                    
                } else {
                    // Normal standing tree - draw all parts from top to bottom
                    Color treeColor = tree.getTreeColor();
                    Color trunkColor = tree.getTrunkColor();
                    
                    for (int i = 0; i < tree.treeArt.length; i++) {
                        String part = tree.treeArt[i];
                        int partWidth = fm.stringWidth(part);
                        
                        // Determine if this part is foliage or trunk
                        boolean isTrunk = i == tree.treeArt.length - 1; // Last part is trunk
                        
                        if (isTrunk) {
                            g.setColor(trunkColor);
                        } else {
                            g.setColor(treeColor);
                        }
                        
                        // Draw each part at the correct vertical position
                        g.drawString(part, screenX - partWidth/2, screenY - (tree.treeArt.length - i - 1) * 16);
                    }
                }
            }
        }

        // Draw player at screen center
        g.setColor(Color.WHITE);
        g.fillRect(screenCenterX - 10, screenCenterY - 10, 20, 20);
        
        // Draw tree cutting prompt if near a tree
        if (showCutPrompt && nearbyTree != null && !nearbyTree.startedFalling) {
            // Draw popup background
            g.setColor(new Color(0, 0, 0, 180)); // Semi-transparent black
            g.fillRoundRect(screenCenterX - 100, screenCenterY - 80, 200, 50, 10, 10);
            
            // Draw popup border
            g.setColor(Color.WHITE);
            g.drawRoundRect(screenCenterX - 100, screenCenterY - 80, 200, 50, 10, 10);
            
            // Draw prompt text
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            FontMetrics promptFm = g.getFontMetrics();
            String promptText = eKeyPressed ? "Cutting tree..." : "Hold 'E' to cut down tree";
            int textWidth = promptFm.stringWidth(promptText);
            g.drawString(promptText, screenCenterX - textWidth/2, screenCenterY - 50);
        }
        
        // Draw coordinate info and wood counter
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("WASD/Arrows to move | World: (" + playerWorldX + ", " + playerWorldY + ")", 10, 20);
        g.drawString("Trees: " + trees.size() + " | Tiles: " + generatedTiles.size(), 10, 35);
        
        // Draw wood counter
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("🪵 Wood: " + woodCount, getWidth() - 150, 25);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        char keyChar = Character.toUpperCase(e.getKeyChar());
        
        int moveSpeed = 15;
        int oldWorldX = playerWorldX;
        int oldWorldY = playerWorldY;
        int newWorldX = playerWorldX;
        int newWorldY = playerWorldY;
        
        // Track E key state
        if (keyChar == 'E') {
            eKeyPressed = true;
        }

        // WASD support
        switch (keyChar) {
            case 'W':
                newWorldY -= moveSpeed;
                break;
            case 'A':
                newWorldX -= moveSpeed;
                break;
            case 'S':
                newWorldY += moveSpeed;
                break;
            case 'D':
                newWorldX += moveSpeed;
                break;
        }

        // Arrow key support
        switch (code) {
            case KeyEvent.VK_UP:
                newWorldY -= moveSpeed;
                break;
            case KeyEvent.VK_LEFT:
                newWorldX -= moveSpeed;
                break;
            case KeyEvent.VK_DOWN:
                newWorldY += moveSpeed;
                break;
            case KeyEvent.VK_RIGHT:
                newWorldX += moveSpeed;
                break;
        }
        
        // Check for collision before moving (both trees and water)
        if (!checkTreeCollision(newWorldX, newWorldY) && !checkWaterCollision(newWorldX, newWorldY)) {
            playerWorldX = newWorldX;
            playerWorldY = newWorldY;
        }
        
        // Update camera to follow player (Pokemon-style)
        cameraX = playerWorldX - screenCenterX;
        cameraY = playerWorldY - screenCenterY;
        
        // Generate new world content if player moved significantly
        if (Math.abs(playerWorldX - oldWorldX) > 0 || Math.abs(playerWorldY - oldWorldY) > 0) {
            generateWorldAroundPlayer();
        }
        
        // Check for nearby trees after movement
        checkNearbyTrees();
        
        // Try to cut tree if E is pressed and we're near one
        if (eKeyPressed) {
            cutDownTree();
        }

        repaint();
    }

    @Override 
    public void keyReleased(KeyEvent e) {
        char keyChar = Character.toUpperCase(e.getKeyChar());
        
        // Reset E key state when released
        if (keyChar == 'E') {
            eKeyPressed = false;
        }
    }
    
    @Override 
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new GPTasciimovement();
    }
}