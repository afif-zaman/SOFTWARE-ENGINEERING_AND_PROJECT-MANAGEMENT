
-- ==========================================
-- AFIFZ MATH WHIZ
-- Database Setup
-- ==========================================

CREATE DATABASE IF NOT EXISTS project_timeline_db;

USE project_timeline_db;

-- ==========================================
-- 1. GAME SESSIONS
-- ==========================================

CREATE TABLE IF NOT EXISTS math_whiz_games (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               player_name VARCHAR(100) NOT NULL,
    total_questions INT NOT NULL DEFAULT 0,
    correct_answers INT NOT NULL DEFAULT 0,
    game_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ended_at TIMESTAMP NULL
    );

-- ==========================================
-- 2. QUESTION ATTEMPTS
-- ==========================================

CREATE TABLE IF NOT EXISTS math_whiz_attempts (
                                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                                  game_id INT NOT NULL,
                                                  operation VARCHAR(20) NOT NULL,
    first_number DOUBLE NOT NULL,
    second_number DOUBLE NULL,
    player_answer VARCHAR(100) NOT NULL,
    correct_answer VARCHAR(100) NOT NULL,
    is_correct BOOLEAN NOT NULL,
    attempted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_math_whiz_game
    FOREIGN KEY (game_id)
    REFERENCES math_whiz_games(id)
    ON DELETE CASCADE
    );

-- ==========================================
-- 3. GAME SCORES AND LEADERBOARD
-- ==========================================

CREATE TABLE IF NOT EXISTS game_scores (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           player_name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    difficulty VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- ==========================================
-- 4. LEADERBOARD QUERY
-- ==========================================

SELECT
    player_name,
    score,
    difficulty,
    played_at
FROM game_scores
ORDER BY score DESC, played_at ASC, id ASC
    LIMIT 10;
