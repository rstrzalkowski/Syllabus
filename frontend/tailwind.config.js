/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      backgroundColor: {
        skin: {
          base: 'var(--color-background-base)',
          muted: 'var(--color-background-muted)',
          inverted: 'var(--color-background-inverted)',
          fill: 'var(--color-background-filled)',
          sidebar: 'var(--color-background-sidebar)'
        }
      },
      textColor: {
        skin: {
          base: 'var(--color-text-base)',
          muted: 'var(--color-text-muted)',
          inverted: 'var(--color-text-inverted)',
          hover: 'var(--color-text-hovered)'
        }
      }
    },
  },
  plugins: [require("@tailwindcss/forms")],
}
