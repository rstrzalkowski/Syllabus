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
        }
      },
      textColor: {
        skin: {
          base: 'var(--color-text-base)',
          muted: 'var(--color-text-muted)',
          inverted: 'var(--color-text-inverted)',
        }
      }
    },
  },
  plugins: [require("@tailwindcss/forms")],
}
