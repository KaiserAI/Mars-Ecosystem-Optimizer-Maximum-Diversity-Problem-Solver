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

The algorithms were rigorously tested to evaluate the trade-off between **solution quality** and **computational cost**.

### 🏆 Comparative Analysis
| Algorithm | Avg. Improvement | Avg. Execution Time | Engineering Insight |
| :--- | :--- | :--- | :--- |
| **GRASP** | **+1.79%** | **0.86s** ⚡ | **Most Efficient.** Reached optimal solutions in sub-second time. |
| **Simulated Annealing** | +1.79% | **0.67s** ⚡ | **Fastest.** Ideal for resource-constrained environments. |
| **Genetic Algorithm** | +1.79% | 14.86s | **Robust.** Consistent convergence but high computational overhead. |
| **Memetic Algorithm** | +1.79% | 21.80s | **Complex.** The local search overhead did not yield extra gains on this specific dataset. |

> **🚀 Key Findings:**
> * **Algorithmic Stability:** Remarkably, all four metaheuristics converged to the **same quality ceiling** (Max Improvement: **+6.22%** on complex instances), proving the robustness of the implemented solution space search.
> * **Efficiency vs. Complexity:** For this specific MDP instance set, **Trajectory-based methods (GRASP/SA)** proved superior, delivering the same solution quality **~20x faster** than the Population-based evolutionary approaches.
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
