# 🚀 Mars Ecosystem Optimizer: Maximum Diversity Problem Solver

![Status](https://img.shields.io/badge/Status-Completed-success)
![Type](https://img.shields.io/badge/Algorithm-Metaheuristics-blue)
![Language](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white)

## 📋 Project Overview

This project implements and benchmarks advanced **Metaheuristic Algorithms** to solve the **Maximum Diversity Problem (MDP)**[.

The application context is the design of a self-sustaining ecosystem for Mars colonization. The goal is to select an optimal subset ($m$) of biological species from a larger pool ($n$) to maximize the **genetic and functional disparity** among them, ensuring ecological resilience in extreme environments.

### 🧮 Mathematical Formulation (MDP)

The problem is modeled as an NP-Hard combinatorial optimization problem aimed at maximizing the sum of distances between selected elements:

$$\text{Maximize } z = \sum_{i=1}^{n-1} \sum_{j=i+1}^{n} d_{ij} x_i x_j$$

Subject to:
$$\sum_{i=1}^{n} x_i = m, \quad x_i \in \{0, 1\}$$

Where $d_{ij}$ represents the disparity/distance between species $i$ and $j$.

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

The algorithms were rigorously tested against a **Random Search baseline** to measure the true impact of intelligent search strategies. The dataset includes instances of varying complexity to evaluate scalability.

### 🏆 Final Results
| Algorithm | Avg. Improvement | Avg. Execution Time | Engineering Verdict |
| :--- | :--- | :--- | :--- |
| **Simulated Annealing** | **+17.33%** | **0.05s** 🚀 | **Best Choice.** Optimal balance: delivers top-tier quality at near-instant speed. |
| **Genetic Algorithm** | **+17.34%** | 1.31s | **High Precision.** Reaches the global optimum consistently but is 26x slower than SA. |
| **Memetic Algorithm** | **+17.34%** | 15.64s | **Overkill.** The local search overhead yields no extra quality gain for this problem type. |
| **GRASP** | +7.00% | **<0.01s** ⚡ | **Fastest.** Good for ultra-low latency, but fails to escape local optima effectively. |

> **🚀 Key Findings:**
> * **The "Quality Ceiling":** Three algorithms (SA, GA, MA) converged to virtually the same optimal solution (**~17.3% improvement**), suggesting they successfully found the global optima for most instances.
> * **Efficiency Surprise:** The **Simulated Annealing (SA)** algorithm emerged as the clear winner. It matched the complex Bio-inspired algorithms in quality but was **orders of magnitude faster** (0.05s vs 15s).
> * **Complexity vs. Value:** The **Memetic Algorithm**, despite being the most complex implementation, did not provide additional value over the standard Genetic Algorithm, proving that for this specific MDP variant, heavy local search is computationally expensive without reward.
---

## 🛠️ Project Structure

The project follows a standard modular architecture separating the domain model from the algorithmic implementations.

```text
/src
  └── /main
       ├── /resources
       │    └── /instances         # Input datasets for MDP (Mars Colonization)
       │
       └── /java/es/urjc/grafo/ABII
            ├── /Model
            │     ├── Instance.java      # Problem data parser & structure
            │     ├── Solution.java      # Genotype representation (Boolean array)
            │     └── Evaluator.java     # Objective function calculation
            │
            └── /Algorithms
                  ├── Algorithm.java     # Interface for strategy pattern
                  ├── /trajectory
                  │     ├── GRASP.java
                  │     └── SimulatedAnnealing.java
                  ├── /population
                  │     ├── GeneticAlgorithm.java
                  │     └── MemeticAlgorithm.java
                  └── Main.java          # Experiment runner & CSV exporter
