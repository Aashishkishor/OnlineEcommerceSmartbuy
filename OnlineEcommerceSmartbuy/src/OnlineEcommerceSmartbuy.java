import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.*;

class OnlineEcommerceSmartbuy{

    public static void main(String[] args) {
        // Set look and feel
        try {
            setLookAndFeel(getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Start the application
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Model Classes
    static class Product {
        private int id;
        private String name;
        private String description;
        private BigDecimal price;
        private String category;
        private int stockQuantity;

        public Product() {}

        public Product(int id, String name, String description, BigDecimal price,
                       String category, int stockQuantity) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.category = category;
            this.stockQuantity = stockQuantity;
        }

        // Getters and Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public int getStockQuantity() { return stockQuantity; }
        public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

        @Override
        public String toString() {
            return String.format("Product{id=%d, name='%s', price=%.2f}", id, name, price);
        }
    }

    static class User {
        private int id;
        private String username;
        private String email;
        private String password;
        private String fullName;
        private String address;
        private String phone;

        public User() {}

        public User(int id, String username, String email, String password,
                    String fullName, String address, String phone) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.password = password;
            this.fullName = fullName;
            this.address = address;
            this.phone = phone;
        }

        // Getters and Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        @Override
        public String toString() {
            return String.format("User{id=%d, username='%s', email='%s'}", id, username, email);
        }
    }

    static class CartItem {
        private int id;
        private int userId;
        private int productId;
        private int quantity;
        private Product product;

        public CartItem() {}

        public CartItem(int id, int userId, int productId, int quantity) {
            this.id = id;
            this.userId = userId;
            this.productId = productId;
            this.quantity = quantity;
        }

        // Getters and Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }
        public int getProductId() { return productId; }
        public void setProductId(int productId) { this.productId = productId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public Product getProduct() { return product; }
        public void setProduct(Product product) { this.product = product; }

        public BigDecimal getTotalPrice() {
            if (product != null && product.getPrice() != null) {
                return product.getPrice().multiply(BigDecimal.valueOf(quantity));
            }
            return BigDecimal.ZERO;
        }
    }

    // Service Classes
    static class AuthService {
        private List<User> users = new ArrayList<>();
        private int nextUserId = 1;

        public AuthService() {
            // Add default admin user
            users.add(new User(1, "admin", "admin@email.com", "admin123",
                    "Administrator", "123 Main St", "555-0123"));
            nextUserId = 2;
        }

        public User login(String username, String password) {
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
            return null;
        }

        public boolean register(User user) {
            // Check if username exists
            if (usernameExists(user.getUsername())) {
                return false;
            }

            // Check if email exists
            if (emailExists(user.getEmail())) {
                return false;
            }

            user.setId(nextUserId++);
            users.add(user);
            return true;
        }

        private boolean usernameExists(String username) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
            return false;
        }

        private boolean emailExists(String email) {
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    return true;
                }
            }
            return false;
        }
    }

    static class ProductService {
        private List<Product> products;

        public ProductService() {
            products = new ArrayList<>();
            // Add sample products
            products.add(new Product(1, "iPhone 14", "Latest Apple smartphone with advanced features",
                    new BigDecimal("999.99"), "Electronics", 50));
            products.add(new Product(2, "Samsung Galaxy S23", "Powerful Android smartphone",
                    new BigDecimal("899.99"), "Electronics", 30));
            products.add(new Product(3, "MacBook Pro", "High-performance laptop for professionals",
                    new BigDecimal("1999.99"), "Computers", 20));
            products.add(new Product(4, "Nike Air Max", "Comfortable running shoes",
                    new BigDecimal("129.99"), "Fashion", 100));
            products.add(new Product(5, "Sony Headphones", "Wireless noise-canceling headphones",
                    new BigDecimal("299.99"), "Electronics", 40));
            products.add(new Product(6, "Apple Watch", "Smartwatch with health monitoring",
                    new BigDecimal("399.99"), "Electronics", 25));
            products.add(new Product(7, "iPad Air", "Lightweight and powerful tablet",
                    new BigDecimal("599.99"), "Electronics", 35));
            products.add(new Product(8, "Canon Camera", "Professional DSLR camera",
                    new BigDecimal("1299.99"), "Electronics", 15));
        }

        public List<Product> getAllProducts() {
            return new ArrayList<>(products);
        }

        public Product getProductById(int id) {
            for (Product product : products) {
                if (product.getId() == id) {
                    return product;
                }
            }
            return null;
        }

        public List<Product> searchProducts(String keyword) {
            List<Product> result = new ArrayList<>();
            String lowerKeyword = keyword.toLowerCase();

            for (Product product : products) {
                if (product.getName().toLowerCase().contains(lowerKeyword) ||
                        product.getDescription().toLowerCase().contains(lowerKeyword) ||
                        product.getCategory().toLowerCase().contains(lowerKeyword)) {
                    result.add(product);
                }
            }
            return result;
        }
    }

    static class CartService {
        private List<CartItem> cartItems = new ArrayList<>();
        private int nextCartItemId = 1;

        public boolean addToCart(int userId, int productId, int quantity) {
            // Check if item already exists in cart
            for (CartItem item : cartItems) {
                if (item.getUserId() == userId && item.getProductId() == productId) {
                    item.setQuantity(item.getQuantity() + quantity);
                    return true;
                }
            }

            // Add new cart item
            CartItem newItem = new CartItem(nextCartItemId++, userId, productId, quantity);
            cartItems.add(newItem);
            return true;
        }

        public List<CartItem> getCartItems(int userId) {
            List<CartItem> userCartItems = new ArrayList<>();
            ProductService productService = new ProductService();

            for (CartItem item : cartItems) {
                if (item.getUserId() == userId) {
                    // Get product details
                    Product product = productService.getProductById(item.getProductId());
                    item.setProduct(product);
                    userCartItems.add(item);
                }
            }
            return userCartItems;
        }

        public boolean removeFromCart(int cartItemId) {
            return cartItems.removeIf(item -> item.getId() == cartItemId);
        }

        public BigDecimal getCartTotal(int userId) {
            BigDecimal total = BigDecimal.ZERO;
            List<CartItem> userCartItems = getCartItems(userId);

            for (CartItem item : userCartItems) {
                total = total.add(item.getTotalPrice());
            }

            return total;
        }

        public void clearCart(int userId) {
            cartItems.removeIf(item -> item.getUserId() == userId);
        }
    }

    // UI Classes
    static class LoginFrame extends JFrame {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton loginButton, registerButton;
        private AuthService authService;

        public LoginFrame() {
            authService = new AuthService();
            initializeUI();
        }

        private void initializeUI() {
            setTitle("E-Commerce - Login");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(450, 400);
            setLocationRelativeTo(null);
            setResizable(false);

            // Main panel
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(240, 240, 240));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Title
            JLabel titleLabel = new JLabel("E-Commerce Store", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
            titleLabel.setForeground(new Color(70, 130, 180));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

            // Login panel
            JPanel loginPanel = new JPanel();
            loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
            loginPanel.setBackground(Color.WHITE);
            loginPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(30, 30, 30, 30)
            ));

            JLabel loginLabel = new JLabel("Login to Your Account", JLabel.CENTER);
            loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
            loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

            // Form panel
            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBackground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            // Username
            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(usernameLabel, gbc);

            usernameField = new JTextField(20);
            usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
            usernameField.setText("admin");
            gbc.gridx = 0;
            gbc.gridy = 1;
            formPanel.add(usernameField, gbc);

            // Password
            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 2;
            formPanel.add(passwordLabel, gbc);

            passwordField = new JPasswordField(20);
            passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
            passwordField.setText("admin123");
            gbc.gridx = 0;
            gbc.gridy = 3;
            formPanel.add(passwordField, gbc);

            // Buttons
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

            loginButton = createStyledButton("Login", new Color(34, 139, 34), 120, 40);
            registerButton = createStyledButton("Register", new Color(70, 130, 180), 120, 40);

            buttonPanel.add(loginButton);
            buttonPanel.add(registerButton);

            // Add components to login panel
            loginPanel.add(loginLabel);
            loginPanel.add(Box.createVerticalStrut(10));
            loginPanel.add(formPanel);
            loginPanel.add(buttonPanel);

            // Add to main panel
            mainPanel.add(titleLabel, BorderLayout.NORTH);
            mainPanel.add(loginPanel, BorderLayout.CENTER);

            add(mainPanel);
            setupEventListeners();
        }

        private JButton createStyledButton(String text, Color bgColor, int width, int height) {
            JButton button = new JButton(text) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                    super.paintComponent(g2);
                    g2.dispose();
                }

                @Override
                protected void paintBorder(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground().darker());
                    g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                    g2.dispose();
                }
            };
            button.setBackground(bgColor);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.setPreferredSize(new Dimension(width, height));
            button.setContentAreaFilled(false);
            button.setOpaque(false);

            // Add hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(bgColor.brighter());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(bgColor);
                }
            });

            return button;
        }

        private void setupEventListeners() {
            loginButton.addActionListener(e -> loginUser());

            registerButton.addActionListener(e -> {
                new RegisterFrame().setVisible(true);
                dispose();
            });

            // Enter key support
            passwordField.addActionListener(e -> loginUser());
        }

        private void loginUser() {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                showError("Please fill in all fields");
                return;
            }

            User user = authService.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new MainFrame(user).setVisible(true);
                dispose();
            } else {
                showError("Invalid username or password");
            }
        }

        private void showError(String message) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static class RegisterFrame extends JFrame {
        private JTextField usernameField, emailField, fullNameField, phoneField, addressField;
        private JPasswordField passwordField, confirmPasswordField;
        private JButton registerButton, backButton;
        private AuthService authService;

        public RegisterFrame() {
            authService = new AuthService();
            initializeUI();
        }

        private void initializeUI() {
            setTitle("E-Commerce - Register");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 600);
            setLocationRelativeTo(null);
            setResizable(false);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(240, 240, 240));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel titleLabel = new JLabel("Create New Account", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(new Color(70, 130, 180));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

            JPanel formPanel = new JPanel(new GridLayout(7, 2, 15, 15));
            formPanel.setBackground(Color.WHITE);
            formPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(30, 30, 30, 30)
            ));

            // Form fields with labels
            addFormField(formPanel, "Username:", usernameField = new JTextField());
            addFormField(formPanel, "Email:", emailField = new JTextField());
            addFormField(formPanel, "Password:", passwordField = new JPasswordField());
            addFormField(formPanel, "Confirm Password:", confirmPasswordField = new JPasswordField());
            addFormField(formPanel, "Full Name:", fullNameField = new JTextField());
            addFormField(formPanel, "Phone:", phoneField = new JTextField());
            addFormField(formPanel, "Address:", addressField = new JTextField());

            // Buttons
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            buttonPanel.setBackground(new Color(240, 240, 240));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

            registerButton = createStyledButton("Register", new Color(34, 139, 34), 120, 40);
            backButton = createStyledButton("Back to Login", new Color(70, 130, 180), 120, 40);

            buttonPanel.add(registerButton);
            buttonPanel.add(backButton);

            mainPanel.add(titleLabel, BorderLayout.NORTH);
            mainPanel.add(formPanel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            add(mainPanel);
            setupEventListeners();
        }

        private void addFormField(JPanel panel, String label, JComponent field) {
            JLabel jLabel = new JLabel(label);
            jLabel.setFont(new Font("Arial", Font.BOLD, 12));
            panel.add(jLabel);
            field.setFont(new Font("Arial", Font.PLAIN, 12));
            field.setPreferredSize(new Dimension(200, 30));
            panel.add(field);
        }

        private JButton createStyledButton(String text, Color bgColor, int width, int height) {
            JButton button = new JButton(text);
            button.setBackground(bgColor);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            button.setPreferredSize(new Dimension(width, height));
            return button;
        }

        private void setupEventListeners() {
            registerButton.addActionListener(e -> registerUser());

            backButton.addActionListener(e -> {
                new LoginFrame().setVisible(true);
                dispose();
            });
        }

        private void registerUser() {
            if (!validateForm()) return;

            User user = new User();
            user.setUsername(usernameField.getText().trim());
            user.setEmail(emailField.getText().trim());
            user.setPassword(new String(passwordField.getPassword()));
            user.setFullName(fullNameField.getText().trim());
            user.setPhone(phoneField.getText().trim());
            user.setAddress(addressField.getText().trim());

            if (authService.register(user)) {
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new LoginFrame().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Registration failed. Username or email may already exist.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private boolean validateForm() {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String fullName = fullNameField.getText().trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() ||
                    confirmPassword.isEmpty() || fullName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        }
    }

    static class MainFrame extends JFrame {
        private User currentUser;
        private ProductService productService;
        private CartService cartService;
        private JPanel productsPanel;
        private JTextField searchField;
        private JComboBox<String> categoryFilter;

        public MainFrame(User user) {
            this.currentUser = user;
            this.productService = new ProductService();
            this.cartService = new CartService();
            initializeUI();
            loadProducts();
        }

        private void initializeUI() {
            setTitle("E-Commerce - Welcome " + currentUser.getUsername());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1200, 800);
            setLocationRelativeTo(null);

            // Create menu bar
            JMenuBar menuBar = new JMenuBar();
            JMenu userMenu = new JMenu("Account");
            JMenuItem cartItem = new JMenuItem("View Cart");
            JMenuItem logoutItem = new JMenuItem("Logout");

            userMenu.add(cartItem);
            userMenu.add(new JSeparator());
            userMenu.add(logoutItem);
            menuBar.add(userMenu);
            setJMenuBar(menuBar);

            // Main content panel
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(237, 15, 15));

            // Header panel
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(new Color(70, 130, 180));
            headerPanel.setPreferredSize(new Dimension(getWidth(), 120));
            headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

            JLabel welcomeLabel = new JLabel("Welcome, " + currentUser.getFullName() + "!", JLabel.LEFT);
            welcomeLabel.setForeground(Color.WHITE);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));

            // Search and filter panel
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            searchPanel.setOpaque(false);

            searchField = new JTextField(25);
            searchField.setFont(new Font("Arial", Font.PLAIN, 14));
            searchField.setPreferredSize(new Dimension(250, 35));

            JButton searchButton = createStyledButton("Search", new Color(34, 139, 34), 100, 35);

            categoryFilter = new JComboBox<>(new String[]{"All Categories", "Electronics", "Computers", "Fashion"});
            categoryFilter.setFont(new Font("Arial", Font.PLAIN, 14));
            categoryFilter.setPreferredSize(new Dimension(180, 35));

            searchPanel.add(new JLabel("Search:"));
            searchPanel.add(searchField);
            searchPanel.add(searchButton);
            searchPanel.add(Box.createHorizontalStrut(20));
            searchPanel.add(new JLabel("Category:"));
            searchPanel.add(categoryFilter);

            headerPanel.add(welcomeLabel, BorderLayout.NORTH);
            headerPanel.add(searchPanel, BorderLayout.SOUTH);

            // Products panel
            productsPanel = new JPanel();
            productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
            productsPanel.setBackground(Color.WHITE);
            JScrollPane scrollPane = new JScrollPane(productsPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            mainPanel.add(headerPanel, BorderLayout.NORTH);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            add(mainPanel);

            // Event listeners
            searchButton.addActionListener(e -> searchProducts());
            categoryFilter.addActionListener(e -> filterProducts());

            cartItem.addActionListener(e -> {
                new CartFrame(currentUser).setVisible(true);
                dispose();
            });

            logoutItem.addActionListener(e -> {
                int result = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    new LoginFrame().setVisible(true);
                    dispose();
                }
            });
        }

        private JButton createStyledButton(String text, Color bgColor, int width, int height) {
            JButton button = new JButton(text);
            button.setBackground(bgColor);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            button.setPreferredSize(new Dimension(width, height));
            return button;
        }

        private void loadProducts() {
            List<Product> products = productService.getAllProducts();
            displayProducts(products);
        }

        private void searchProducts() {
            String keyword = searchField.getText().trim();
            if (!keyword.isEmpty()) {
                List<Product> products = productService.searchProducts(keyword);
                displayProducts(products);
            } else {
                loadProducts();
            }
        }

        private void filterProducts() {
            String selectedCategory = (String) categoryFilter.getSelectedItem();
            if ("All Categories".equals(selectedCategory)) {
                loadProducts();
            } else {
                List<Product> allProducts = productService.getAllProducts();
                List<Product> filteredProducts = new ArrayList<>();
                for (Product product : allProducts) {
                    if (selectedCategory.equals(product.getCategory())) {
                        filteredProducts.add(product);
                    }
                }
                displayProducts(filteredProducts);
            }
        }

        private void displayProducts(List<Product> products) {
            productsPanel.removeAll();

            if (products.isEmpty()) {
                JLabel noProductsLabel = new JLabel("No products found", JLabel.CENTER);
                noProductsLabel.setFont(new Font("Arial", Font.ITALIC, 18));
                noProductsLabel.setForeground(Color.GRAY);
                noProductsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                productsPanel.add(Box.createVerticalStrut(100));
                productsPanel.add(noProductsLabel);
            } else {
                // Create a grid panel for products
                JPanel gridPanel = new JPanel(new GridLayout(0, 3, 25, 25));
                gridPanel.setBackground(Color.WHITE);
                gridPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

                for (Product product : products) {
                    gridPanel.add(createProductCard(product));
                }

                productsPanel.add(gridPanel);
            }

            productsPanel.revalidate();
            productsPanel.repaint();
        }

        private JPanel createProductCard(Product product) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
            ));
            card.setPreferredSize(new Dimension(280, 200));

            // Product info
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);

            JLabel nameLabel = new JLabel(product.getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setForeground(new Color(70, 130, 180));
            nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel priceLabel = new JLabel("$" + product.getPrice());
            priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
            priceLabel.setForeground(new Color(34, 139, 34));
            priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel categoryLabel = new JLabel("Category: " + product.getCategory());
            categoryLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            categoryLabel.setForeground(Color.DARK_GRAY);
            categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel stockLabel = new JLabel("In Stock: " + product.getStockQuantity());
            stockLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            stockLabel.setForeground(Color.DARK_GRAY);
            stockLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            infoPanel.add(nameLabel);
            infoPanel.add(Box.createVerticalStrut(8));
            infoPanel.add(priceLabel);
            infoPanel.add(Box.createVerticalStrut(8));
            infoPanel.add(categoryLabel);
            infoPanel.add(Box.createVerticalStrut(4));
            infoPanel.add(stockLabel);
            infoPanel.add(Box.createVerticalStrut(15));

            // Add to cart button
            JButton addToCartButton = createStyledButton("Add to Cart", new Color(34, 139, 34), 120, 35);
            addToCartButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            addToCartButton.addActionListener(e -> addToCart(product));

            infoPanel.add(addToCartButton);

            card.add(infoPanel, BorderLayout.CENTER);

            return card;
        }

        private void addToCart(Product product) {
            boolean success = cartService.addToCart(currentUser.getId(), product.getId(), 1);
            if (success) {
                JOptionPane.showMessageDialog(this,
                        product.getName() + " added to cart!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to add product to cart", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    static class CartFrame extends JFrame {
        private User currentUser;
        private CartService cartService;
        private JPanel cartItemsPanel;
        private JLabel totalLabel;
        private List<CartItem> cartItems;

        public CartFrame(User user) {
            this.currentUser = user;
            this.cartService = new CartService();
            initializeUI();
            loadCartItems();
        }

        private void initializeUI() {
            setTitle("Shopping Cart - " + currentUser.getUsername());
            setSize(900, 700);
            setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(0x202));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

            JLabel titleLabel = new JLabel("Your Shopping Cart", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
            titleLabel.setForeground(new Color(70, 130, 180));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));

            // Cart items panel
            cartItemsPanel = new JPanel();
            cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
            cartItemsPanel.setBackground(Color.WHITE);
            JScrollPane scrollPane = new JScrollPane(cartItemsPanel);
            scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            // Total and checkout panel
            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBackground(Color.WHITE);
            bottomPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(20, 25, 20, 25)
            ));

            totalLabel = new JLabel("Total: $0.00", JLabel.LEFT);
            totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
            totalLabel.setForeground(new Color(34, 139, 34));

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
            buttonPanel.setBackground(Color.WHITE);

            JButton checkoutButton = createStyledButton("Proceed to Checkout", new Color(34, 139, 34), 160, 45);
            JButton continueShoppingButton = createStyledButton("Continue Shopping", new Color(70, 130, 180), 160, 45);
            JButton clearCartButton = createStyledButton("Clear Cart", new Color(220, 53, 69), 120, 45);

            checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
            continueShoppingButton.setFont(new Font("Arial", Font.BOLD, 14));
            clearCartButton.setFont(new Font("Arial", Font.BOLD, 14));

            checkoutButton.addActionListener(e -> checkout());
            continueShoppingButton.addActionListener(e -> {
                new MainFrame(currentUser).setVisible(true);
                dispose();
            });
            clearCartButton.addActionListener(e -> clearCart());

            buttonPanel.add(continueShoppingButton);
            buttonPanel.add(clearCartButton);
            buttonPanel.add(checkoutButton);

            bottomPanel.add(totalLabel, BorderLayout.WEST);
            bottomPanel.add(buttonPanel, BorderLayout.EAST);

            mainPanel.add(titleLabel, BorderLayout.NORTH);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);

            add(mainPanel);
        }

        private JButton createStyledButton(String text, Color bgColor, int width, int height) {
            JButton button = new JButton(text);
            button.setBackground(bgColor);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            button.setPreferredSize(new Dimension(width, height));
            return button;
        }

        private void loadCartItems() {
            cartItems = cartService.getCartItems(currentUser.getId());
            displayCartItems();
            updateTotal();
        }

        private void displayCartItems() {
            cartItemsPanel.removeAll();

            if (cartItems.isEmpty()) {
                JLabel emptyLabel = new JLabel("Your cart is empty", JLabel.CENTER);
                emptyLabel.setFont(new Font("Arial", Font.ITALIC, 18));
                emptyLabel.setForeground(Color.GRAY);
                emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                cartItemsPanel.add(Box.createVerticalStrut(150));
                cartItemsPanel.add(emptyLabel);
            } else {
                for (CartItem item : cartItems) {
                    cartItemsPanel.add(createCartItemPanel(item));
                    cartItemsPanel.add(Box.createVerticalStrut(15));
                }
            }

            cartItemsPanel.revalidate();
            cartItemsPanel.repaint();
        }

        private JPanel createCartItemPanel(CartItem item) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                    BorderFactory.createEmptyBorder(15, 20, 15, 20)
            ));
            itemPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));

            // Product info
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(Color.WHITE);

            JLabel nameLabel = new JLabel(item.getProduct().getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

            JLabel priceLabel = new JLabel("Price: $" + item.getProduct().getPrice() + " x " + item.getQuantity());
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            JLabel totalLabel = new JLabel("Total: $" + item.getTotalPrice());
            totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
            totalLabel.setForeground(new Color(139, 71, 34));

            infoPanel.add(nameLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(priceLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(totalLabel);

            // Remove button
            JButton removeButton = createStyledButton("Remove", new Color(220, 53, 69), 100, 35);
            removeButton.setFont(new Font("Arial", Font.BOLD, 12));
            removeButton.addActionListener(e -> removeItem(item.getId()));

            itemPanel.add(infoPanel, BorderLayout.CENTER);
            itemPanel.add(removeButton, BorderLayout.EAST);

            return itemPanel;
        }

        private void removeItem(int cartItemId) {
            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to remove this item?", "Remove Item",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                boolean success = cartService.removeFromCart(cartItemId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Item removed from cart", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    loadCartItems();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to remove item", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private void updateTotal() {
            BigDecimal total = cartService.getCartTotal(currentUser.getId());
            totalLabel.setText("Total: $" + total);
        }

        private void clearCart() {
            if (cartItems.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cart is already empty", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int result = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to clear your entire cart?", "Clear Cart",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                cartService.clearCart(currentUser.getId());
                JOptionPane.showMessageDialog(this, "Cart cleared", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                loadCartItems();
            }
        }

        private void checkout() {
            if (cartItems.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Your cart is empty", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            BigDecimal total = cartService.getCartTotal(currentUser.getId());
            int result = JOptionPane.showConfirmDialog(this,
                    "Proceed with checkout? Total: $" + total, "Checkout",
                    JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                cartService.clearCart(currentUser.getId());
                JOptionPane.showMessageDialog(this,
                        "Order placed successfully! Thank you for your purchase.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                new MainFrame(currentUser).setVisible(true);
                dispose();
            }
        }
    }
}