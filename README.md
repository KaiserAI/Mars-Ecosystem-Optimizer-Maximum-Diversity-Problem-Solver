# 🚀 Mars Ecosystem Optimizer: Maximum Diversity Problem Solver

![Status](https://img.shields.io/badge/Status-Completed-success)
![Type](https://img.shields.io/badge/Algorithm-Metaheuristics-blue)
![Language](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white)

## 📋 Project Overview

[cite_start]This project implements and benchmarks advanced **Metaheuristic Algorithms** to solve the **Maximum Diversity Problem (MDP)**[cite: 22].

The application context is the design of a self-sustaining ecosystem for Mars colonization. [cite_start]The goal is to select an optimal subset ($m$) of biological species from a larger pool ($n$) to maximize the **genetic and functional disparity** among them, ensuring ecological resilience in extreme environments[cite: 20].

### 🧮 Mathematical Formulation (MDP)

[cite_start]The problem is modeled as an NP-Hard combinatorial optimization problem aimed at maximizing the sum of distances between selected elements[cite: 23, 26]:

$$\text{Maximize } z = \sum_{i=1}^{n-1} \sum_{j=i+1}^{n} d_{ij} x_i x_j$$

Subject to:
$$\sum_{i=1}^{n} x_i = m, \quad x_i \in \{0, 1\}$$

[cite_start]Where $d_{ij}$ represents the disparity/distance between species $i$ and $j$[cite: 26].

---

## 🧠 Implemented Algorithms

To address the computational complexity, two distinct algorithmic approaches were engineered and compared:

### 1. Trajectory-Based Methods (Single-Solution)
Iterative algorithms designed to escape local optima by exploring the search space through neighborhood structures.
* **GRASP (Greedy Randomized Adaptive Search Procedure):** Combines a greedy randomized construction with a Local Search phase.
* **Simulated Annealing (SA):** A probabilistic technique approximating the global optimum by allowing "uphill" moves to avoid getting trapped in local minima.

### 2. Population-Based Methods (Bio-Inspired)
Evolutionary strategies that maintain and improve a population of candidate solutions.
* **Genetic Algorithm (GA):** Mimics natural selection using crossover, mutation, and selection operators.
* **Memetic Algorithm (MA):** A hybrid approach combining the evolutionary framework of GAs with a separate Local Search procedure for individual refinement (Lamarckian evolution).

---

## 📊 Performance & Benchmarking

[cite_start]The algorithms were tested against a strict baseline (Random Constructive Search) [cite: 45] on high-complexity instances.

* [cite_start]**Constraints:** Maximum execution time of **60 seconds** per instance[cite: 50].
* **Objective:** Maximize the diversity score ($z$).

| Algorithm | Solution Quality (Fitness) | Stability | Convergence Speed |
| :--- | :--- | :--- | :--- |
| **GRASP** | ⭐⭐⭐ | ⭐⭐⭐⭐ | Fast |
| **Simulated Annealing** | ⭐⭐⭐ | ⭐⭐⭐ | Medium |
| **Genetic Algorithm** | ⭐⭐⭐⭐ | ⭐⭐⭐⭐ | Slow |
| **Memetic Algorithm** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ | Very Slow |

> **Key Insight:** The **Memetic Algorithm** consistently outperformed other approaches, achieving solutions significantly superior to the baseline by effectively balancing *exploration* (evolution) and *exploitation* (local search).

---

## 🛠️ Project Structure

```text
/src
  ├── /algorithms
  │     ├── /trajectory    # GRASP and Simulated Annealing implementations
  │     └── /population    # Genetic and Memetic implementations
  ├── /model               # Data structures (Solution, Instance)
  ├── /utils               # File parsing and metrics
  └── Main.java            # Experiment runner
