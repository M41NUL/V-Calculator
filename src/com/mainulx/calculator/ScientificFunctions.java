/*
 * ============================================
 * V-CALCULATOR - Scientific Functions
 * ============================================
 * 
 * Developer Information:
 * --------------------------------------------
 * Author     : Md. Mainul Islam
 * Owner      : MAINUL - X
 * GitHub     : M41NUL
 * GitHub URL : https://github.com/M41NUL
 * WhatsApp   : +8801308850528
 * Telegram   : @mdmainulislaminfo
 * Email      : githubmainul@gmail.com
 * License    : MIT License
 * Copyright  : Copyright (c) 2026 MAINUL - X
 * ============================================
 */

package com.mainulx.calculator;

/**
 * ScientificFunctions class - Handles all mathematical operations
 * Contains methods for basic arithmetic and scientific calculations
 */
public class ScientificFunctions {
    
    // Constants
    private static final double PI = Math.PI;
    private static final double E = Math.E;
    private static final double EPSILON = 1e-10; // For floating point comparison
    
    /**
     * Constructor
     */
    public ScientificFunctions() {
        // Initialize if needed
    }
    
    // ==================== BASIC OPERATIONS ====================
    
    /**
     * Addition of two numbers
     * @param a First number
     * @param b Second number
     * @return Sum of a and b
     */
    public double add(double a, double b) {
        return a + b;
    }
    
    /**
     * Subtraction of two numbers
     * @param a First number
     * @param b Second number
     * @return Difference (a - b)
     */
    public double subtract(double a, double b) {
        return a - b;
    }
    
    /**
     * Multiplication of two numbers
     * @param a First number
     * @param b Second number
     * @return Product of a and b
     */
    public double multiply(double a, double b) {
        return a * b;
    }
    
    /**
     * Division of two numbers
     * @param a First number (dividend)
     * @param b Second number (divisor)
     * @return Quotient (a / b)
     * @return Double.NaN if division by zero
     */
    public double divide(double a, double b) {
        if (Math.abs(b) < EPSILON) {
            return Double.NaN; // Division by zero error
        }
        return a / b;
    }
    
    /**
     * Power function (a^b)
     * @param a Base
     * @param b Exponent
     * @return a raised to power b
     */
    public double power(double a, double b) {
        return Math.pow(a, b);
    }
    
    /**
     * Square root of a number
     * @param a Input number
     * @return Square root of a
     * @return Double.NaN if a is negative
     */
    public double sqrt(double a) {
        if (a < 0) {
            return Double.NaN; // Square root of negative number
        }
        return Math.sqrt(a);
    }
    
    /**
     * Cube root of a number
     * @param a Input number
     * @return Cube root of a
     */
    public double cbrt(double a) {
        return Math.cbrt(a);
    }
    
    // ==================== TRIGONOMETRIC FUNCTIONS ====================
    
    /**
     * Sine function
     * @param angle Angle in degrees or radians
     * @param isDegree true if angle is in degrees, false if in radians
     * @return Sine of the angle
     */
    public double sin(double angle, boolean isDegree) {
        double rad = isDegree ? Math.toRadians(angle) : angle;
        return Math.sin(rad);
    }
    
    /**
     * Cosine function
     * @param angle Angle in degrees or radians
     * @param isDegree true if angle is in degrees, false if in radians
     * @return Cosine of the angle
     */
    public double cos(double angle, boolean isDegree) {
        double rad = isDegree ? Math.toRadians(angle) : angle;
        return Math.cos(rad);
    }
    
    /**
     * Tangent function
     * @param angle Angle in degrees or radians
     * @param isDegree true if angle is in degrees, false if in radians
     * @return Tangent of the angle
     */
    public double tan(double angle, boolean isDegree) {
        double rad = isDegree ? Math.toRadians(angle) : angle;
        
        // Check for undefined values (cos = 0)
        double cos = Math.cos(rad);
        if (Math.abs(cos) < EPSILON) {
            return Double.NaN; // Tan undefined
        }
        return Math.tan(rad);
    }
    
    /**
     * Inverse Sine (arcsine)
     * @param value Input value (must be between -1 and 1)
     * @param isDegree true if result in degrees, false if in radians
     * @return Arcsin of value
     */
    public double asin(double value, boolean isDegree) {
        if (value < -1 || value > 1) {
            return Double.NaN; // Out of domain
        }
        double rad = Math.asin(value);
        return isDegree ? Math.toDegrees(rad) : rad;
    }
    
    /**
     * Inverse Cosine (arccosine)
     * @param value Input value (must be between -1 and 1)
     * @param isDegree true if result in degrees, false if in radians
     * @return Arccos of value
     */
    public double acos(double value, boolean isDegree) {
        if (value < -1 || value > 1) {
            return Double.NaN; // Out of domain
        }
        double rad = Math.acos(value);
        return isDegree ? Math.toDegrees(rad) : rad;
    }
    
    /**
     * Inverse Tangent (arctangent)
     * @param value Input value
     * @param isDegree true if result in degrees, false if in radians
     * @return Arctan of value
     */
    public double atan(double value, boolean isDegree) {
        double rad = Math.atan(value);
        return isDegree ? Math.toDegrees(rad) : rad;
    }
    
    /**
     * Arctangent with two arguments (atan2)
     * @param y Y coordinate
     * @param x X coordinate
     * @param isDegree true if result in degrees, false if in radians
     * @return Angle whose tangent is y/x
     */
    public double atan2(double y, double x, boolean isDegree) {
        double rad = Math.atan2(y, x);
        return isDegree ? Math.toDegrees(rad) : rad;
    }
    
    // ==================== LOGARITHMIC FUNCTIONS ====================
    
    /**
     * Natural logarithm (base e)
     * @param a Input number (must be positive)
     * @return ln(a)
     * @return Double.NaN if a <= 0
     */
    public double ln(double a) {
        if (a <= 0) {
            return Double.NaN; // Logarithm of non-positive number
        }
        return Math.log(a);
    }
    
    /**
     * Common logarithm (base 10)
     * @param a Input number (must be positive)
     * @return log10(a)
     * @return Double.NaN if a <= 0
     */
    public double log(double a) {
        if (a <= 0) {
            return Double.NaN; // Logarithm of non-positive number
        }
        return Math.log10(a);
    }
    
    /**
     * Logarithm with custom base
     * @param a Input number (must be positive)
     * @param base Logarithm base (must be positive and not 1)
     * @return log_base(a)
     */
    public double logBase(double a, double base) {
        if (a <= 0 || base <= 0 || Math.abs(base - 1) < EPSILON) {
            return Double.NaN;
        }
        return Math.log(a) / Math.log(base);
    }
    
    // ==================== EXPONENTIAL FUNCTIONS ====================
    
    /**
     * Exponential function (e^x)
     * @param a Exponent
     * @return e raised to power a
     */
    public double exp(double a) {
        return Math.exp(a);
    }
    
    /**
     * 10^x function
     * @param a Exponent
     * @return 10 raised to power a
     */
    public double exp10(double a) {
        return Math.pow(10, a);
    }
    
    // ==================== FACTORIAL & COMBINATORICS ====================
    
    /**
     * Factorial of a number
     * @param n Input number (must be non-negative integer)
     * @return n!
     * @return Double.NaN if n is negative or not an integer
     */
    public double factorial(double n) {
        // Check if n is a non-negative integer
        if (n < 0 || Math.abs(n - Math.round(n)) > EPSILON) {
            return Double.NaN; // Factorial only for non-negative integers
        }
        
        int num = (int) Math.round(n);
        
        // 0! = 1
        if (num == 0) return 1;
        
        // Calculate factorial
        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
            
            // Check for overflow
            if (result < 0) {
                return Double.POSITIVE_INFINITY; // Too large
            }
        }
        
        return result;
    }
    
    /**
     * Permutation: P(n, r) = n! / (n-r)!
     * @param n Total items
     * @param r Selected items
     * @return Number of permutations
     */
    public double permutation(double n, double r) {
        if (n < 0 || r < 0 || r > n || 
            Math.abs(n - Math.round(n)) > EPSILON || 
            Math.abs(r - Math.round(r)) > EPSILON) {
            return Double.NaN;
        }
        
        int nInt = (int) Math.round(n);
        int rInt = (int) Math.round(r);
        
        double result = 1;
        for (int i = nInt; i > nInt - rInt; i--) {
            result *= i;
        }
        
        return result;
    }
    
    /**
     * Combination: C(n, r) = n! / (r! * (n-r)!)
     * @param n Total items
     * @param r Selected items
     * @return Number of combinations
     */
    public double combination(double n, double r) {
        double perm = permutation(n, r);
        if (Double.isNaN(perm)) return Double.NaN;
        
        return perm / factorial(r);
    }
    
    // ==================== OTHER FUNCTIONS ====================
    
    /**
     * Absolute value
     * @param a Input number
     * @return |a|
     */
    public double abs(double a) {
        return Math.abs(a);
    }
    
    /**
     * Reciprocal (1/x)
     * @param a Input number
     * @return 1/a
     * @return Double.NaN if a is zero
     */
    public double inverse(double a) {
        if (Math.abs(a) < EPSILON) {
            return Double.NaN; // Division by zero
        }
        return 1.0 / a;
    }
    
    /**
     * Negation (-x)
     * @param a Input number
     * @return -a
     */
    public double negate(double a) {
        return -a;
    }
    
    /**
     * Square of a number
     * @param a Input number
     * @return a²
     */
    public double square(double a) {
        return a * a;
    }
    
    /**
     * Cube of a number
     * @param a Input number
     * @return a³
     */
    public double cube(double a) {
        return a * a * a;
    }
    
    /**
     * 10^x function
     * @param a Input number
     * @return 10^a
     */
    public double tenPower(double a) {
        return Math.pow(10, a);
    }
    
    /**
     * Modulo (remainder)
     * @param a Dividend
     * @param b Divisor
     * @return a % b
     */
    public double modulo(double a, double b) {
        if (Math.abs(b) < EPSILON) {
            return Double.NaN; // Modulo by zero
        }
        return a % b;
    }
    
    // ==================== CONSTANTS ====================
    
    /**
     * Get Pi constant
     * @return π
     */
    public double getPi() {
        return PI;
    }
    
    /**
     * Get Euler's number
     * @return e
     */
    public double getE() {
        return E;
    }
    
    /**
     * Get Tau constant (2π)
     * @return τ
     */
    public double getTau() {
        return 2 * PI;
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Round to specified decimal places
     * @param value Input value
     * @param places Number of decimal places
     * @return Rounded value
     */
    public double round(double value, int places) {
        if (places < 0) return value;
        
        double factor = Math.pow(10, places);
        return Math.round(value * factor) / factor;
    }
    
    /**
     * Convert degrees to radians
     * @param degrees Angle in degrees
     * @return Angle in radians
     */
    public double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }
    
    /**
     * Convert radians to degrees
     * @param radians Angle in radians
     * @return Angle in degrees
     */
    public double toDegrees(double radians) {
        return Math.toDegrees(radians);
    }
    
    /**
     * Check if a value is valid (not NaN or Infinite)
     * @param value Input value
     * @return true if valid
     */
    public boolean isValid(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value);
    }
    
    /**
     * Get error message for invalid result
     * @param value Input value that caused error
     * @return Error message
     */
    public String getErrorMessage(double value) {
        if (Double.isNaN(value)) {
            return "Math Error";
        } else if (Double.isInfinite(value)) {
            return "Overflow Error";
        }
        return "Unknown Error";
    }
}
