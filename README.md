## City Graph Visualization & Algorithms Simulator (Java)
A **Java-based graph simulation and visualization system** that models cities and roads, allowing users to visualize and compare classic graph algorithms in real time.

This project focuses on **Object-Oriented Design (OOD)**, **clean architecture**, and **graph algorithms**, with the entire system designed using **UML before implementation**.


https://github.com/user-attachments/assets/c49ee82d-0d71-4217-b930-4750d1716480

## UML diagram
PDF https://github.com/abdelrahman390/City_Simulation_with_Graph/blob/master/UML%20diagram/final%20UML.pdf

<img width="2932" height="1354" alt="New UML design drawio" src="https://github.com/user-attachments/assets/51810055-a54d-4fdc-bb42-fe194a39c465" />




---

## ✨ Features

### 🗺️ Graph Modeling
- Cities represented as **nodes** with coordinates
- Roads represented as **weighted edges**
- Supports **directed and undirected graphs**

### 🧠 Algorithm Implementations
- **Dijkstra’s Algorithm**  
  Single-source shortest path between two nodes
- **Floyd–Warshall Algorithm**  
  All-pairs shortest paths with **precomputed and cached results**
- **Kruskal’s Algorithm**  
  Minimum Spanning Tree (MST) using **Disjoint Set Union (DSU)**

### 🧩 Object-Oriented Architecture
Clear separation of concerns across packages:
- `model` → Core graph structures (`Node`, `Edge`, `Graph`)
- `algorithm` → Graph algorithms (Shortest Path & MST)
- `service` → Utility services (random selection, edge direction, weight calculation)
- `controller` → Algorithm orchestration and coordination
- `ui` → Visualization, rendering, and user interaction

---

## 🧠 Architecture Overview

- Algorithms are **fully decoupled from the UI**
- Each algorithm returns a unified `AlgorithmResult`
- Graph is built once and reused across all algorithms
- Designed with **SOLID principles** in mind
- UML-driven development ensures scalability and maintainability

---

## 🛠 Technologies

- **Java**
- **Object-Oriented Programming (OOP)**
- **SOLID Principles**
- **Graph Theory & Data Structures**
- **UML Design**

