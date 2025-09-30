# Métricas de Rendimiento

- **Tiempo Secuencial:** 46ms
- **Tiempo Paralelo:** 31ms
- **Procesadores utilizados:** 2

---

## Análisis de Rendimiento

**Speedup:** 46ms / 31ms = **1.48 veces más rápido**
> El speedup mide cuánto más rápida es la versión paralela en comparación con la secuencial.

**Eficiencia:** (1.48 / 2) * 100 = **74.2%**
> La eficiencia indica qué tan bien se aprovecharon los recursos del procesador. Una eficiencia del 100% sería ideal.

**Overhead:** (2 * 31ms) - 46ms = **16ms de gestión**
> El overhead es el tiempo extra que se consume en tareas de gestión (como crear y sincronizar hilos) en lugar de en el cálculo principal.