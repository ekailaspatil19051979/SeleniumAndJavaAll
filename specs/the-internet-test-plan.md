# The Internet - Comprehensive Test Plan

## Application Overview

The Internet (https://the-internet.herokuapp.com/) is a comprehensive web application designed for practicing automated testing. It contains multiple examples of common web elements and challenging scenarios that testers encounter in real-world applications, including form interactions, dynamic content, authentication, file operations, and various UI components.

## Test Scenarios

### 1. Authentication and Login Tests

**Seed:** `tests/seed.spec.ts`

#### 1.1. Valid Login

**File:** `tests/authentication/valid-login.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/login
  2. Enter username 'tomsmith' in the username field
  3. Enter password 'SuperSecretPassword!' in the password field
  4. Click the Login button
  5. Verify successful login message appears
  6. Verify user is redirected to secure area page

**Expected Results:**
  - Login form should be visible and accessible
  - Username field should accept input
  - Password field should mask input
  - Login button should be clickable
  - Success message 'You logged into a secure area!' should be displayed
  - URL should change to /secure and secure area content should be visible

#### 1.2. Invalid Login Attempts

**File:** `tests/authentication/invalid-login.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/login
  2. Test invalid username with correct password
  3. Test valid username with incorrect password
  4. Test empty username and password fields
  5. Test special characters in username and password
  6. Verify error messages are displayed appropriately

**Expected Results:**
  - Error message should appear for invalid credentials
  - User should remain on login page
  - Form should not submit with empty fields
  - Error message should be clear and informative
  - No sensitive information should be exposed in error messages

#### 1.3. Logout Functionality

**File:** `tests/authentication/logout.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/login
  2. Login with valid credentials (tomsmith/SuperSecretPassword!)
  3. Click the Logout button in the secure area
  4. Verify logout message appears
  5. Verify user is redirected back to login page

**Expected Results:**
  - User should be successfully logged in first
  - Logout button should be visible in secure area
  - Logout message 'You logged out of the secure area!' should be displayed
  - User should be redirected to login page
  - Secure area should no longer be accessible without re-authentication

### 2. Form Interactions and Validation

**Seed:** `tests/seed.spec.ts`

#### 2.1. Basic Form Controls

**File:** `tests/forms/basic-controls.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/inputs
  2. Test input field with various numeric values
  3. Test input field with non-numeric values
  4. Test input field boundaries (min/max values)
  5. Navigate to checkboxes page and test checkbox interactions
  6. Navigate to dropdown page and test dropdown selections

**Expected Results:**
  - Input field should accept numeric values
  - Input field should reject or handle non-numeric input appropriately
  - Boundary values should be handled correctly
  - Checkboxes should toggle states correctly
  - Dropdown should allow selection of different options

#### 2.2. File Upload Functionality

**File:** `tests/forms/file-upload.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/upload
  2. Select a valid file for upload
  3. Click the Upload button
  4. Verify successful upload message
  5. Test upload with different file types
  6. Test upload with large files
  7. Test upload without selecting a file

**Expected Results:**
  - File selection dialog should open
  - Selected file name should be displayed
  - Upload should complete successfully for valid files
  - Success message should confirm upload
  - Different file types should be handled appropriately
  - Large files should either upload or show appropriate error
  - Upload without file selection should show validation error

### 3. Dynamic Content and JavaScript Interactions

**Seed:** `tests/seed.spec.ts`

#### 3.1. Dynamic Loading Content

**File:** `tests/dynamic/dynamic-loading.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/dynamic_loading/1
  2. Click the Start button
  3. Wait for loading indicator to appear
  4. Wait for content to be revealed
  5. Navigate to dynamic_loading/2 and repeat test
  6. Verify different loading behaviors

**Expected Results:**
  - Start button should be clickable
  - Loading indicator should appear after clicking Start
  - Hidden content should become visible after loading completes
  - Loading time should be reasonable
  - Both dynamic loading examples should work correctly

#### 3.2. Add Remove Elements

**File:** `tests/dynamic/add-remove-elements.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/add_remove_elements/
  2. Click 'Add Element' button multiple times
  3. Verify new Delete buttons are added
  4. Click Delete buttons to remove elements
  5. Verify elements are removed from DOM
  6. Test adding and removing elements in different sequences

**Expected Results:**
  - Add Element button should be functional
  - Each click should add a new Delete button
  - Delete buttons should be properly labeled
  - Clicking Delete should remove the corresponding element
  - DOM should update correctly with additions and removals

#### 3.3. Drag and Drop Interactions

**File:** `tests/dynamic/drag-and-drop.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/drag_and_drop
  2. Identify source and target elements (A and B)
  3. Perform drag and drop from A to B
  4. Verify elements have swapped positions
  5. Perform drag and drop from B to A
  6. Verify elements return to original positions

**Expected Results:**
  - Two draggable elements should be visible
  - Elements should be draggable
  - Drop operation should be successful
  - Elements should swap positions after drag and drop
  - Text content should update to reflect new positions

### 4. Navigation and Page Interactions

**Seed:** `tests/seed.spec.ts`

#### 4.1. Multiple Windows Handling

**File:** `tests/navigation/multiple-windows.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/windows
  2. Click 'Click Here' link to open new window
  3. Switch to new window
  4. Verify new window content
  5. Switch back to original window
  6. Verify original window content
  7. Close new window

**Expected Results:**
  - Link should be clickable
  - New window should open
  - New window should contain expected content
  - Should be able to switch between windows
  - Original window should remain functional
  - Windows should close properly

#### 4.2. Context Menu Interactions

**File:** `tests/navigation/context-menu.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/context_menu
  2. Right-click on the designated area
  3. Verify context menu appears
  4. Handle the JavaScript alert
  5. Test context menu in different areas of the page

**Expected Results:**
  - Right-click should trigger context menu
  - JavaScript alert should appear
  - Alert should contain expected message
  - Alert should be dismissible
  - Context menu behavior should be consistent

#### 4.3. Frames and Nested Content

**File:** `tests/navigation/frames.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/frames
  2. Test nested frames navigation
  3. Navigate to iframe page
  4. Switch to iframe content
  5. Interact with iframe elements
  6. Switch back to main frame

**Expected Results:**
  - Frame links should be accessible
  - Should be able to navigate between frame examples
  - Iframe content should be accessible
  - Should be able to interact with iframe elements
  - Frame switching should work correctly

### 5. Advanced UI Components and Edge Cases

**Seed:** `tests/seed.spec.ts`

#### 5.1. Hover Interactions

**File:** `tests/ui-components/hover-interactions.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/hovers
  2. Hover over each user avatar
  3. Verify user information appears on hover
  4. Click on 'View profile' links
  5. Verify profile pages load correctly
  6. Test hover behavior with keyboard navigation

**Expected Results:**
  - User avatars should be visible
  - Hovering should reveal user information
  - User names and profile links should appear
  - Profile links should be clickable
  - Profile pages should load with correct content

#### 5.2. Challenging DOM Interactions

**File:** `tests/ui-components/challenging-dom.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/challenging_dom
  2. Interact with the three buttons (different styles)
  3. Verify table content is present
  4. Test table data accessibility
  5. Verify canvas element is present
  6. Test button functionality and state changes

**Expected Results:**
  - Three buttons with different styles should be visible
  - Buttons should be clickable
  - Table should contain data rows
  - Table content should be accessible
  - Canvas element should be rendered

#### 5.3. Notification Messages

**File:** `tests/ui-components/notification-messages.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/notification_message_rendered
  2. Click the link to trigger notification
  3. Verify notification message appears
  4. Check message content variations
  5. Test notification timing and behavior
  6. Verify message dismissal

**Expected Results:**
  - Link should be clickable
  - Notification should appear after clicking
  - Message content should be readable
  - Notification should be properly styled
  - Message should disappear or be dismissible

#### 5.4. Floating Menu Navigation

**File:** `tests/ui-components/floating-menu.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/floating_menu
  2. Verify floating menu is visible
  3. Scroll down the page
  4. Verify menu remains floating/fixed
  5. Click on menu items
  6. Verify navigation to page sections

**Expected Results:**
  - Floating menu should be visible on page load
  - Menu should remain visible while scrolling
  - Menu items should be clickable
  - Clicking menu items should navigate to correct sections
  - Menu position should remain fixed during scroll

### 6. Error Handling and Edge Cases

**Seed:** `tests/seed.spec.ts`

#### 6.1. Broken Images and Missing Resources

**File:** `tests/error-handling/broken-resources.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/broken_images
  2. Identify all image elements on the page
  3. Check which images fail to load
  4. Verify broken image handling
  5. Test page layout with broken images
  6. Verify alt text for broken images

**Expected Results:**
  - Page should load despite broken images
  - Broken images should be identifiable
  - Page layout should not be severely affected
  - Alt text should be displayed for broken images
  - At least some images should load correctly

#### 6.2. Redirect Handling

**File:** `tests/error-handling/redirects.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/redirector
  2. Click the redirect link
  3. Verify redirect occurs
  4. Check final destination URL
  5. Test redirect status codes
  6. Verify redirect chain handling

**Expected Results:**
  - Redirect link should be clickable
  - Page should redirect to new location
  - Final URL should be different from initial
  - Redirect should complete successfully
  - Page content should load after redirect

#### 6.3. Status Codes Testing

**File:** `tests/error-handling/status-codes.spec.ts`

**Steps:**
  1. Navigate to https://the-internet.herokuapp.com/status_codes
  2. Click on different status code links (200, 301, 404, 500)
  3. Verify appropriate status codes are returned
  4. Check error page content for 404 and 500
  5. Verify successful responses for 200 and 301
  6. Test browser handling of different status codes

**Expected Results:**
  - Status code links should be accessible
  - Each link should return the expected status code
  - Error pages should display appropriate content
  - 200 status should show success content
  - 301 redirect should work correctly
  - Browser should handle all status codes appropriately
